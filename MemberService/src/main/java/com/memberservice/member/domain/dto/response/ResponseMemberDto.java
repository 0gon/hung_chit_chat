package com.memberservice.member.domain.dto.response;

import com.memberservice.member.domain.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseMemberDto {

    private String memberId;
    private String email;
    private Role role;

    @Builder
    public ResponseMemberDto(String memberId, String email, Role role) {
        this.memberId = memberId;
        this.email = email;
        this.role = role;
    }
}
