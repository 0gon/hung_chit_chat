package com.redis.redisChat.demo.server.domain.memberRoom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberRoomDto {
    private Long id;
    private String memberId;

    public static MemberRoomDto of(MemberRoom memberRoom) {
        return new MemberRoomDto(memberRoom.getId()
                , memberRoom.getMember().getId());
    }
}
