package com.example.feedservice.service;

import com.example.feedservice.entity.PostEntity;
import com.example.feedservice.repository.PostRepository;
import com.example.feedservice.util.FeedUtil;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
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

    @Value("${upload.path}")
    private String uploadPath;

    @DisplayName("파일 업로드 테스트")
    @Test
    public void fileUploadTest() throws Exception{

        long startTime = System.currentTimeMillis();

        String postId = feedUtil.getUUID();
        PostEntity post = PostEntity.builder()
                .postId(postId)
                .memberId("1234")
                .build();

        postRepository.save(post);

        List<MultipartFile> multipartFiles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            multipartFiles.add(new MockMultipartFile("testImage" + i,  i + "testImage.png", "png", "testImage".getBytes(StandardCharsets.UTF_8)));
        }


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

        Assertions.assertThat(count).isEqualTo(3);

    }

}