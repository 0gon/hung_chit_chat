package com.redis.redisChat.demo.server.domain.member.dto;

import com.redis.redisChat.demo.server.domain.member.entity.Member;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

    private String id;
    private String nickName;

    public static MemberDto of(Member member) {
        return new MemberDto(member.getId(), member.getNickName());
    }
}
