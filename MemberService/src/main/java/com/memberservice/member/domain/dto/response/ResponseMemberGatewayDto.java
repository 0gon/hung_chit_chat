package com.memberservice.member.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * API GATEWAY SERVICE 사용
 * */
@Getter
@Setter
@Builder
public class ResponseMemberGatewayDto {

    private Long id;
    private String memberId;
    private String email;
}
