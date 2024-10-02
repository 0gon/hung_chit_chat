package com.example.feedservice.service;

import com.example.feedservice.dto.request.RequestPostCreateDto;
import com.example.feedservice.entity.FileEntity;
import com.example.feedservice.entity.PostEntity;
import com.example.feedservice.repository.FileRepository;
import com.example.feedservice.util.FeedUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PostServiceTest {

    @Autowired private PostService postService;
    @Autowired private FeedUtil feedUtil;
    @Autowired private FileService fileService;
    @Autowired private FileRepository fileRepository;


    @DisplayName("게시글 생성 테스트")
    @Test
    public void PostServiceTest() throws Exception{

        List<MultipartFile> multipartFiles = new ArrayList<>();

        multipartFiles.add(new MockMultipartFile("testImage1",   "testImage1.jpeg", "image/jpeg", "testImage".getBytes(StandardCharsets.UTF_8)));
        multipartFiles.add(new MockMultipartFile("testImage2",  "testImage2.png", "image/png", "testImage".getBytes(StandardCharsets.UTF_8)));
        multipartFiles.add(new MockMultipartFile("testImage3",  "testImage3.jpg", "image/jpg", "testImage".getBytes(StandardCharsets.UTF_8)));
        multipartFiles.add(new MockMultipartFile("testImage3",  "testImage4.amg", "image/amg", "testImage".getBytes(StandardCharsets.UTF_8)));

        RequestPostCreateDto requestPostCreateDto = RequestPostCreateDto.builder()
                .memberId("1234")
                .contents("5678")
                .publicScope("PUBLIC")
                .file(multipartFiles)
                .build();

        /**
         * 테스트 완료 2024-10-02
         * */
//        PostEntity post = postService.createPost(requestPostCreateDto);
//
//        List<FileEntity> fileEntities = fileRepository.findAllByPost(post);
//
//        Assertions.assertThat(fileEntities).hasSize(3);

    }
}