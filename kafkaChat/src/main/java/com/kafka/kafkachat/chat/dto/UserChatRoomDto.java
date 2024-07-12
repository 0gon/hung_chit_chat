package com.kafka.kafkachat.chat.dto;

import com.kafka.kafkachat.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserChatRoomDto {

    private Long id;
    private MemberDto memberDto;
}
