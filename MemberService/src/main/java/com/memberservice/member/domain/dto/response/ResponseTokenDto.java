package com.memberservice.member.domain.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ResponseTokenDto {

    private String accessToken;
    private String refreshToken;

    @Builder
    public ResponseTokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
