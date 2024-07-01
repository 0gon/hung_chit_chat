package com.kafka.kafkachat.chat.dto;

import com.kafka.kafkachat.chat.entity.ChatMessage;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDto {

    private Long id;
    private Long senderId;
    private String message;
    private Long chatRoomId;

    public ChatMessageDto(ChatMessage chatMessage) {
        this.senderId = chatMessage.getSender().getId();
        this.message = chatMessage.getMessage();
        this.chatRoomId = chatMessage.getChatRoom().getId();
    }

    public ChatMessage toEntity(ChatMessageDto chatMessageDto) {
        return ChatMessage.builder()
                .message(chatMessageDto.message)
                .sendDate(LocalDateTime.now())
                .build();
    }

}
