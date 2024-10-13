package com.example.feedservice.feed.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class RequestFeedUpdateDto {

    private String feedId;

    private String publicScope;

    private String contents;

    private List<MultipartFile> media;

    @Builder
    public RequestFeedUpdateDto(String feedId, String publicScope, String contents, List<MultipartFile> media) {
        this.feedId = feedId;
        this.publicScope = publicScope;
        this.contents = contents;
        this.media = media;
    }
}
