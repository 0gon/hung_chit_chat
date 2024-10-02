package com.example.feedservice.service;

import com.example.feedservice.entity.FileEntity;
import com.example.feedservice.entity.PostEntity;
import com.example.feedservice.repository.FileRepository;
import com.example.feedservice.repository.PostRepository;
import com.example.feedservice.util.FeedUtil;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileServiceTest {

    @Autowired private FeedUtil feedUtil;
    @Autowired private FileService fileService;
    @Autowired private PostRepository postRepository;
    @Autowired private FileRepository fileRepository;
    @Autowired private EntityManager em;

    @Value("${upload.path}")
    private String uploadPath;

    @DisplayName("파일 업로드 테스트")
    @Test
    @Transactional
    public void fileUploadTest() throws Exception{

        long startTime = System.currentTimeMillis();

        String postId = feedUtil.getUUID();
        PostEntity post = PostEntity.builder()
                .postId(postId)
                .memberId("1234")
                .build();

        postRepository.save(post);

        List<MultipartFile> multipartFiles = new ArrayList<>();

        multipartFiles.add(new MockMultipartFile("testImage1",   "testImage1.jpeg", "image/jpeg", "testImage".getBytes(StandardCharsets.UTF_8)));
        multipartFiles.add(new MockMultipartFile("testImage2",  "testImage2.png", "image/png", "testImage".getBytes(StandardCharsets.UTF_8)));
        multipartFiles.add(new MockMultipartFile("testImage3",  "testImage3.jpg", "image/jpg", "testImage".getBytes(StandardCharsets.UTF_8)));
        multipartFiles.add(new MockMultipartFile("testImage3",  "testImage4.amg", "image/amg", "testImage".getBytes(StandardCharsets.UTF_8)));
//        multipartFiles.add(new MockMultipartFile("testImage3",  "testImage3.jpg", "image/jpg", Files.readAllBytes(Paths.get(uploadPath + "/" + "test.jpg"))));    // 테스트 완료


        fileService.uploadFileAtStore(post, multipartFiles);

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println("seconds : " + (double)timeElapsed / 1000);

        if(!Files.exists(Paths.get(uploadPath + "/" + postId))){
            // 테스트 실패
            fail();
        }

        // 파일 갯수 체크
        long count = Files.list(Paths.get(uploadPath + "/" + postId)).count();

        List<FileEntity> fileEntities2 = fileRepository.findAllByPost(post);
        Assertions.assertThat(count).isEqualTo(3);

        // 연관관계 확인
        PostEntity postEntity = postRepository.findById(post.getPostId()).get();        // post 객체와 postEntity 객체는 다른 객체 -> 65번 라인 로직에서 연관관계 매핑 -> 객체 주소가 달라짐
        List<FileEntity> fileEntities = fileRepository.findAllByPost(postEntity);

        Assertions.assertThat(fileEntities).hasSize(3);
        Assertions.assertThat(fileEntities.get(0).getPost().getPostId()).isEqualTo(post.getPostId());
        Assertions.assertThat(fileEntities.get(1).getPost().getPostId()).isEqualTo(post.getPostId());
        Assertions.assertThat(fileEntities.get(2).getPost().getPostId()).isEqualTo(post.getPostId());
        Assertions.assertThat(post.getFileList().get(0).getPost().getPostId()).isEqualTo(fileEntities.get(0).getPost().getPostId());


        Assertions.assertThat(fileEntities.get(0).getPost()).isEqualTo(postEntity);
        Assertions.assertThat(fileEntities.get(1).getPost()).isEqualTo(postEntity);
        Assertions.assertThat(fileEntities.get(2).getPost()).isEqualTo(postEntity);

    }

}