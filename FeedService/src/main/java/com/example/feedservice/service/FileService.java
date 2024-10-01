package com.example.feedservice.service;

import com.example.feedservice.entity.FileEntity;
import com.example.feedservice.entity.PostEntity;
import com.example.feedservice.repository.FileRepository;
import com.example.feedservice.util.FeedUtil;
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
public class FileService {

    private final FileRepository fileRepository;

    private final FeedUtil feedUtil;

    @Value("${upload.path}")
    private String uploadDir;

    /**
     * 파일 저장
     * @param post, fileList - PostEntity, List<MultipartFile>
     * */
    public void uploadFileAtStore(PostEntity post, List<MultipartFile> fileList) throws IOException {

        List<FileEntity> fileEntities = new ArrayList<>();


        Path directoryPath = Paths.get(uploadDir);
        if(!Files.exists(directoryPath)){
            Files.createDirectory(directoryPath);
        }

        // uploadDir/post_id 로 폴더 생성
        Path savedDirectoryPath = Paths.get(directoryPath + "/" + post.getPostId());
        Files.createDirectory(savedDirectoryPath);


        // uploadDir/post_id 폴더에 파일 저장
        for (MultipartFile multipartFile : fileList) {
            String fileId = feedUtil.getUUID();
            String pathName = savedDirectoryPath + "/" + fileId;
            multipartFile.transferTo(new File(pathName));

            FileEntity fileEntity = FileEntity.builder()
                    .fileId(fileId)
                    .filePath(pathName)
                    .fileSize(multipartFile.getSize())
                    .fileName(multipartFile.getOriginalFilename())
                    .build();

            fileEntities.add(fileEntity);
        }

        // 연관관계 설정
        for (FileEntity fileEntity : fileEntities) {
            post.addFile(fileEntity);
        }

        fileRepository.saveAll(fileEntities);

    }
}
