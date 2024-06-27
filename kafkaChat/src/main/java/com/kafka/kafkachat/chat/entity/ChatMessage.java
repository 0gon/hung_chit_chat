package com.kafka.kafkachat.chat.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime sendDate;

    private Long senderId;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(LocalDateTime sendDate, Long senderId, String message, ChatRoom chatRoom) {
        this.sendDate = sendDate;
        this.senderId = senderId;
        this.message = message;
        this.chatRoom = chatRoom;
    }

}
