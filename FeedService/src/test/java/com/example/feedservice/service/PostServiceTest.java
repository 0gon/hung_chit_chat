package com.example.feedservice.service;

import com.example.feedservice.dto.request.RequestPostCreateDto;
import com.example.feedservice.util.FeedUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

    @Autowired private PostService postService;
    @Autowired private FeedUtil feedUtil;
    @Autowired private FileService fileService;


    @DisplayName("게시글 생성 테스트")
    @Test
    public void PostServiceTest() throws Exception{

//        RequestPostCreateDto.builder()
//                        .
        //postService.createPost();

    }
}