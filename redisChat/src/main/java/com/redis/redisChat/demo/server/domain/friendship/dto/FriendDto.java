package com.redis.redisChat.demo.server.domain.friendship.dto;


import com.redis.redisChat.demo.server.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class FriendDto {
    private String id;
    private String nickName;

    public static FriendDto of(Member member) {
        return new FriendDto(member.getId(), member.getNickName());
    }
}
