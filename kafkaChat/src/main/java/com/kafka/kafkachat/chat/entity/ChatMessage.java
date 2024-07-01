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


    private LocalDateTime sendDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member sender;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @Builder
    public ChatMessage(LocalDateTime sendDate, Member sender, String message, ChatRoom chatRoom) {
        this.sendDate = sendDate;
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
        chatRoom.addChatMessage(this);
    }
}
