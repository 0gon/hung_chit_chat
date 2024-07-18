package com.redis.redisChat.demo.server.domain.member.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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