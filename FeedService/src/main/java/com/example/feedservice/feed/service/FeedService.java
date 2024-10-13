package com.example.feedservice.feed.service;

import com.example.feedservice.feed.dto.request.RequestFeedUpdateDto;
import com.example.feedservice.media.service.MediaService;
import com.example.feedservice.feed.dto.request.RequestFeedCreateDto;
import com.example.feedservice.feed.entity.FeedEntity;
import com.example.feedservice.feed.repository.FeedRepository;
import com.example.feedservice.common.util.FeedUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedService {

    private final FeedRepository feedRepository;
    private final MediaService mediaService;
    private final FeedUtil feedUtil;

    @Transactional
    public void createFeed(RequestFeedCreateDto requestFeedCreateDto){

        String feedId = feedUtil.getUUID();

        // feed Entity 생성
        FeedEntity feed = FeedEntity.builder()
                .feedId(feedId)
                .memberId(requestFeedCreateDto.getMemberId())
                .contents(requestFeedCreateDto.getContents())
                .publicScope(requestFeedCreateDto.getPublicScope())
                .build();

        try {
            // File 로컬에 저장 및 연관관계 매핑, 영속성 컨텍스트에 들어간 Post Entity 저장
            mediaService.uploadMediaAtStore(feed, requestFeedCreateDto.getMedia());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file : " + e);
        }

    }

    @Transactional
    public void patchFeed(RequestFeedUpdateDto requestFeedUpdateDto){

        FeedEntity feedEntity = feedRepository.findById(requestFeedUpdateDto.getFeedId()).orElseThrow(() -> new IllegalArgumentException("Feed Not Found"));
        feedEntity.changeContents(requestFeedUpdateDto.getContents());      // 내용 변경

        try {
            mediaService.updateMediaAtStore(feedEntity, requestFeedUpdateDto.getMedia());
        } catch(Exception e){
            throw new RuntimeException("Failed to update media : " + e);
        }
    }

}
