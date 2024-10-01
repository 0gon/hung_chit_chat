package com.example.feedservice.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestPostCreateDto {

    private String postId;
    private String memberId;
    private String publicScope;
    private String contents;
    private List<MultipartFile> file;

    public RequestPostCreateDto(RequestPostCreateDto requestPostCreateDto) {
    }

    @Builder
    public RequestPostCreateDto(String postId, String memberId, String publicScope, String contents, List<MultipartFile> file) {
        this.postId = postId;
        this.memberId = memberId;
        this.publicScope = publicScope;
        this.contents = contents;
        this.file = file;
    }
}
