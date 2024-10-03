package com.memberservice.member.domain.dto.response;

import com.memberservice.member.domain.entity.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMemberDto {

    private String memberId;
    private String email;
    private Role role;
    private String password;

    @Builder
    public ResponseMemberDto(String memberId, String email, Role role, String password) {
        this.memberId = memberId;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}
