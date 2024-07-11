package com.redis.redisChat.demo.domain.member.entity;


import java.util.List;

import com.redis.redisChat.demo.domain.memberRoom.entity.MemberRoom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;

    private String nickName;

    // @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    // private List<MemberRoom> MemberRooms;

}