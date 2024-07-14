package com.redis.redisChat.demo.domain.room;

import java.util.ArrayList;
import java.util.List;

import com.redis.redisChat.demo.domain.member.entity.Member;
import com.redis.redisChat.demo.domain.memberRoom.entity.MemberRoom;
import com.redis.redisChat.demo.domain.room.entity.Room;
import com.redis.redisChat.demo.domain.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
