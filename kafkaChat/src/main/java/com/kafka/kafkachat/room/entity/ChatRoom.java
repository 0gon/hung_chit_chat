package com.kafka.kafkachat.room.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chatRooms")
    private List<UserChatRoom> rooms = new ArrayList<>();





    // ===          연관관계 편의 메서드         === //
    public void setUserChatRoom(UserChatRoom room) {
        this.rooms.add(room);
        room.setChatRoom(this);
    }

}
