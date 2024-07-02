package com.kafka.kafkachat.chat.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kafka.kafkachat.member.entity.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Member sender;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    private LocalDateTime timestamp;

    @Builder
    public ChatMessage(Member sender, String message, ChatRoom chatRoom, LocalDateTime timestamp) {
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
        this.timestamp = timestamp;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        chatRoom.addChatMessage(this);
    }
}
