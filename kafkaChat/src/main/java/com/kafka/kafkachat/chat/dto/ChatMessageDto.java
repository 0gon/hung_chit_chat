package com.kafka.kafkachat.chat.dto;

import com.kafka.kafkachat.chat.entity.ChatMessage;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDto {

    private Long senderId;
    private String message;
    private String chatRoomName;

    public ChatMessageDto(ChatMessage chatMessage) {
        this.senderId = chatMessage.getSenderId();
        this.message = chatMessage.getMessage();
    }

    public ChatMessage toEntity(ChatMessageDto chatMessageDto) {
        return ChatMessage.builder()
                .senderId(chatMessageDto.senderId)
                .message(chatMessageDto.message)
                .sendDate(LocalDateTime.now())
                .build();
    }

}
