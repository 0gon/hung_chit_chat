package com.redis.redisChat.demo.server.domain.room.entity;

import com.redis.redisChat.demo.server.domain.memberRoom.entity.MemberRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room {
    
    @Id @GeneratedValue
    private Long id; // 나중에 서버id + 숫자로 변경하기 ex)Snowflake
    
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MemberRoom> MemberRooms = new ArrayList<>();

    public void addMemberRoom(MemberRoom memberRoom) {
        this.MemberRooms.add(memberRoom);
        memberRoom.setRoom(this);
    }
}
