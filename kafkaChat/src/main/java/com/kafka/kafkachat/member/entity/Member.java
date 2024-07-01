package com.kafka.kafkachat.member.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kafka.kafkachat.chat.entity.ChatMessage;
import com.kafka.kafkachat.chat.entity.UserChatRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    public Member(String username) {
        this.username = username;
    }


    @Builder
    Member(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    // ===          연관관계 편의 메서드         === //
    public void addUserChatRoom(UserChatRoom userChatRooms) {
        this.userChatRooms.add(userChatRooms);
        userChatRooms.setMember(this);
    }
}
