package com.kafka.kafkachat.member.entity;

import com.kafka.kafkachat.room.entity.UserChatRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    public Member(String username) {
        this.username = username;
    }

    // ===          연관관계 편의 메서드         === //
    public void addUserChatRoom(UserChatRoom userChatRooms) {
        this.userChatRooms.add(userChatRooms);
        userChatRooms.setMember(this);
    }
}
