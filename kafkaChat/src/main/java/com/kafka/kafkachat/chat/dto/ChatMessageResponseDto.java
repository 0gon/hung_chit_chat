package com.kafka.kafkachat.chat.dto;

import com.kafka.kafkachat.chat.entity.ChatMessage;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ChatMessageResponseDto {

    private Long id;
    private Long senderId;
    private String message;
    private String senderName;
    private Long chatRoomId;
    private String timestamp;


    public ChatMessageResponseDto(ChatMessage chatMessage) {
        this.senderId = chatMessage.getSender().getId();
        this.message = chatMessage.getMessage();
        this.senderName = chatMessage.getSender().getUsername();
        this.chatRoomId = chatMessage.getChatRoom().getId();
        this.timestamp = chatMessage.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
