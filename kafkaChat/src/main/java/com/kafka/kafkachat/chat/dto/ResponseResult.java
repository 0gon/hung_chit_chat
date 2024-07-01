package com.kafka.kafkachat.chat.dto;

import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.member.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseResult {
    public ChatRoom chatRoom;
    public Member member;
}
