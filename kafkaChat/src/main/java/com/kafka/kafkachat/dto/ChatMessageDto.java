package com.kafka.kafkachat.dto;

import com.kafka.kafkachat.entity.ChatMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class ChatMessageDto {

    private final Long senderId;
    private final String message;
    private final Long roomId;

    public ChatMessage toEntity(ChatMessageDto chatMessageDto) {
        return ChatMessage.builder()
                .senderId(chatMessageDto.senderId)
                .message(chatMessageDto.message)
                .roomId(chatMessageDto.roomId)
                .build();
    }

}
