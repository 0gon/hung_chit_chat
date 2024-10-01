package com.example.feedservice.service;

import com.example.feedservice.dto.request.RequestPostCreateDto;
import com.example.feedservice.entity.FileEntity;
import com.example.feedservice.entity.PostEntity;
import com.example.feedservice.repository.PostRepository;
import com.example.feedservice.util.FeedUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final FileService fileService;
    private final FeedUtil feedUtil;

    @Transactional
    public void createPost(RequestPostCreateDto requestPostCreateDto){

        String postId = feedUtil.getUUID();

        // Post Entity 생성
        PostEntity post = PostEntity.builder()
                .postId(postId)
                .memberId(requestPostCreateDto.getMemberId())
                .contents(requestPostCreateDto.getContents())
                .publicScope(requestPostCreateDto.getPublicScope())
                .build();

        try {
            // File 로컬에 저장 및 연관관계 매핑
            fileService.uploadFileAtStore(post, requestPostCreateDto.getFile());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file : " + e);
        }

        postRepository.save(post);
    }

}
