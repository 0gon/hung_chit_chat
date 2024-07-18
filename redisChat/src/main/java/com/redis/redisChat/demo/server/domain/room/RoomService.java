package com.redis.redisChat.demo.server.domain.room;

import com.redis.redisChat.demo.server.domain.member.entity.Member;
import com.redis.redisChat.demo.server.domain.memberRoom.entity.MemberRoom;
import com.redis.redisChat.demo.server.domain.room.entity.Room;
import com.redis.redisChat.demo.server.domain.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository repository;

    public void makeRoom(List<String> memberIds) {
        System.out.println("memberIds: " + memberIds);
        Room room = new Room(null, new ArrayList<>());
        for (String friendId : memberIds) {
            Member member = new Member(friendId, null);
            MemberRoom memberRoom = new MemberRoom();
            memberRoom.setMember(member);
            room.addMemberRoom(memberRoom);
        }

        repository.save(room);
    }


}
