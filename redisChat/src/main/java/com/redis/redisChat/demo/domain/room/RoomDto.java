package com.redis.redisChat.demo.domain.room;

import com.redis.redisChat.demo.domain.memberRoom.entity.MemberRoom;
import com.redis.redisChat.demo.domain.memberRoom.entity.MemberRoomDto;
import com.redis.redisChat.demo.domain.room.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Long id;
    private List<MemberRoomDto> MemberRooms = new ArrayList<>();

    public static RoomDto of(Room room) {
        Long roomId = room.getId();
        List<MemberRoom> memberRooms = room.getMemberRooms();
        List<MemberRoomDto> memberRoomDtos = memberRooms.stream()
                .map(MemberRoomDto::of)
                .toList();
        return new RoomDto(
                roomId,
                memberRoomDtos
        );
    }
}
