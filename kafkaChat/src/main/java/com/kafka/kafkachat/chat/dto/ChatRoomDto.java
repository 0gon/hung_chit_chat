package com.kafka.kafkachat.chat.dto;

import com.kafka.kafkachat.chat.entity.ChatRoom;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ChatRoomDto {
    private Long id;
    private String name;
}
