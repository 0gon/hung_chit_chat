package com.kafka.kafkachat.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatCreatedDto {

    // 초대한사람
    private Long senderId;

    // 초대받는사람
    private Long recipientId;

    private String roomName;
}
