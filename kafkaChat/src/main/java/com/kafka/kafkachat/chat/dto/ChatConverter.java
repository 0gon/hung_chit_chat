package com.kafka.kafkachat.chat.dto;

import com.kafka.kafkachat.chat.entity.ChatRoom;
import lombok.Getter;

@Getter
public class ChatConverter {

    public static ChatRoom toEntity(ChatRoomDto chatRoomDto) {
        return ChatRoom.builder()
                .id(chatRoomDto.getId())
                .name(chatRoomDto.getName())
                .build();
    }
}
