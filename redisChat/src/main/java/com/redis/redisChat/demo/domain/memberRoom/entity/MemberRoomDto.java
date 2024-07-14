package com.redis.redisChat.demo.domain.memberRoom.entity;

import com.redis.redisChat.demo.domain.member.dto.MemberDto;
import com.redis.redisChat.demo.domain.room.entity.Room;
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
