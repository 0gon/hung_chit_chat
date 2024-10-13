package com.example.feedservice.media.service;

import com.example.feedservice.media.entity.MediaEntity;
import com.example.feedservice.feed.entity.FeedEntity;
import com.example.feedservice.media.repository.MediaRepository;
import com.example.feedservice.feed.repository.FeedRepository;
import com.example.feedservice.common.util.FeedUtil;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MediaServiceTest {

    @Autowired private FeedUtil feedUtil;
    @Autowired private MediaService mediaService;
    @Autowired private FeedRepository feedRepository;
    @Autowired private MediaRepository mediaRepository;
    @Autowired private EntityManager em;

    @Value("${upload.path}")
    private String uploadPath;

    @DisplayName("파일 업로드 테스트")
    @Test
    @Transactional
    public void fileUploadTest() throws Exception{

        long startTime = System.currentTimeMillis();

        String postId = feedUtil.getUUID();
        FeedEntity feed = FeedEntity.builder()
                .feedId(postId)
                .memberId("1234")
                .build();

        feedRepository.save(feed);

        List<MultipartFile> multipartFiles = new ArrayList<>();

        multipartFiles.add(new MockMultipartFile("testImage1",   "testImage1.jpeg", "image/jpeg", "testImage".getBytes(StandardCharsets.UTF_8)));
        multipartFiles.add(new MockMultipartFile("testImage2",  "testImage2.png", "image/png", "testImage".getBytes(StandardCharsets.UTF_8)));
        multipartFiles.add(new MockMultipartFile("testImage3",  "testImage3.jpg", "image/jpg", "testImage".getBytes(StandardCharsets.UTF_8)));
        multipartFiles.add(new MockMultipartFile("testImage3",  "testImage4.amg", "image/amg", "testImage".getBytes(StandardCharsets.UTF_8)));
//        multipartFiles.add(new MockMultipartFile("testImage3",  "testImage3.jpg", "image/jpg", Files.readAllBytes(Paths.get(uploadPath + "/" + "test.jpg"))));    // 테스트 완료


        mediaService.uploadMediaAtStore(feed, multipartFiles);

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println("seconds : " + (double)timeElapsed / 1000);

        if(!Files.exists(Paths.get(uploadPath + "/" + postId))){
            // 테스트 실패
            fail();
        }

        // 파일 갯수 체크
        long count = Files.list(Paths.get(uploadPath + "/" + postId)).count();

        // 연관관계 확인
        FeedEntity postEntity = feedRepository.findById(feed.getFeedId()).get();        // post 객체와 postEntity 객체는 다른 객체 -> 65번 라인 로직에서 연관관계 매핑 -> 객체 주소가 달라짐
        List<MediaEntity> fileEntities = mediaRepository.findAllByFeed(postEntity);

        Assertions.assertThat(fileEntities).hasSize(3);
        Assertions.assertThat(fileEntities.get(0).getFeed().getFeedId()).isEqualTo(feed.getFeedId());
        Assertions.assertThat(fileEntities.get(1).getFeed().getFeedId()).isEqualTo(feed.getFeedId());
        Assertions.assertThat(fileEntities.get(2).getFeed().getFeedId()).isEqualTo(feed.getFeedId());
        Assertions.assertThat(feed.getMediaList().get(0).getFeed().getFeedId()).isEqualTo(fileEntities.get(0).getFeed().getFeedId());


        Assertions.assertThat(fileEntities.get(0).getFeed()).isEqualTo(postEntity);
        Assertions.assertThat(fileEntities.get(1).getFeed()).isEqualTo(postEntity);
        Assertions.assertThat(fileEntities.get(2).getFeed()).isEqualTo(postEntity);

    }

}