package com.example.feedservice.media.service;

import com.example.feedservice.feed.entity.FeedEntity;
import com.example.feedservice.media.entity.MediaEntity;
import com.example.feedservice.media.repository.MediaRepository;
import com.example.feedservice.common.util.FeedUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;

    private final FeedUtil feedUtil;

    @Value("${upload.path}")
    private String uploadDir;

    /**
     * 파일 저장
     * @param feed, fileList - feedEntity, List<MultipartFile>
     * */
    public void uploadMediaAtStore(FeedEntity feed, List<MultipartFile> mediaList) throws IOException, RuntimeException {

        List<MediaEntity> fileEntities = new ArrayList<>();

        Path directoryPath = Paths.get(uploadDir);
        if(!Files.exists(directoryPath)){
            Files.createDirectory(directoryPath);
        }

        // uploadDir/feed_id 로 폴더 생성
        Path savedDirectoryPath = Paths.get(directoryPath + "/" + feed.getFeedId());
        Files.createDirectory(savedDirectoryPath);


        // uploadDir/post_id 폴더에 파일 저장
        for (MultipartFile multipartFile : mediaList) {
            String mediaId = feedUtil.getUUID();
            String extension = "";
            String contentType = multipartFile.getContentType();

            if (contentType == null || (
                            !contentType.equals("image/jpeg") &&
                            !contentType.equals("image/png") &&
                            !contentType.equals("image/gif") &&
                            !contentType.equals("image/jpg")
            )){
                // 지원하지 않는 형식이면 continue;
                continue;
            }

            extension = contentType.substring(contentType.lastIndexOf("/") + 1);

            String pathName = savedDirectoryPath + "/" + mediaId + "." + extension;
            multipartFile.transferTo(new File(pathName));

            MediaEntity mediaEntity = MediaEntity.builder()
                    .mediaId(mediaId)
                    .mediaPath(pathName)
                    .mediaSize(multipartFile.getSize())
                    .mediaName(multipartFile.getOriginalFilename())
                    .build();

            // 연관관계 설정
            mediaEntity.setFeed(feed);

            fileEntities.add(mediaEntity);

        }

        mediaRepository.saveAll(fileEntities);

    }

    public void updateMediaAtStore(FeedEntity feed, List<MultipartFile> mediaList) throws IOException, RuntimeException {

        List<MediaEntity> fileEntities = new ArrayList<>();

        List<MediaEntity> mediaEntities = mediaRepository.findByFeedId(feed.getFeedId());

        if (mediaEntities == null) {
            // uploadDir/feed_id 가 없으면 폴더 생성
            Path savedDirectoryPath = Paths.get(uploadDir + "/" + feed.getFeedId());
            if (!Files.exists(savedDirectoryPath)) {
                Files.createDirectory(savedDirectoryPath);
            }
        }

        // uploadDir/post_id
//        if (mediaEntities != null) {
//            for (MultipartFile multipartFile : mediaList) {
//
//                String mediaId = feedUtil.getUUID();
//                String extension = "";
//                String contentType = multipartFile.getContentType();
//
//                if (contentType == null || (
//                        !contentType.equals("image/jpeg") &&
//                                !contentType.equals("image/png") &&
//                                !contentType.equals("image/gif") &&
//                                !contentType.equals("image/jpg")
//                )) {
//                    // 지원하지 않는 형식이면 continue;
//                    continue;
//                }
//
//                extension = contentType.substring(contentType.lastIndexOf("/") + 1);
//
//                String pathName = savedDirectoryPath + "/" + mediaId + "." + extension;
//                multipartFile.transferTo(new File(pathName));
//
//                MediaEntity mediaEntity = MediaEntity.builder()
//                        .mediaId(mediaId)
//                        .mediaPath(pathName)
//                        .mediaSize(multipartFile.getSize())
//                        .mediaName(multipartFile.getOriginalFilename())
//                        .build();
//
//                // 연관관계 설정
//                mediaEntity.setFeed(feed);
//
//                fileEntities.add(mediaEntity);
//
//            }
//
//            mediaRepository.saveAll(fileEntities);
//        }
    }
}
