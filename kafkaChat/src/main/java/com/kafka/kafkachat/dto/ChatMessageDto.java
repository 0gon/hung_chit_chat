package com.kafka.kafkachat.dto;

import com.kafka.kafkachat.entity.ChatMessage;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDto {

    private Long senderId;
    private String message;
    private Long roomId;

    public ChatMessageDto(ChatMessage chatMessage) {
        this.senderId = chatMessage.getSenderId();
        this.message = chatMessage.getMessage();
        this.roomId = chatMessage.getRoomId();
    }

    public ChatMessage toEntity(ChatMessageDto chatMessageDto) {
        return ChatMessage.builder()
                .senderId(chatMessageDto.senderId)
                .message(chatMessageDto.message)
                .roomId(chatMessageDto.roomId)
                .sendDate(LocalDateTime.now())
                .build();
    }

}
