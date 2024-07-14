package com.redis.redisChat.demo.domain.room.entity;

import java.util.ArrayList;
import java.util.List;

import com.redis.redisChat.demo.domain.memberRoom.entity.MemberRoom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
