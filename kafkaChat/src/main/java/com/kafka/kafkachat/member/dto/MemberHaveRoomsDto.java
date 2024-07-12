package com.kafka.kafkachat.member.dto;

import com.kafka.kafkachat.chat.dto.ChatRoomDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MemberHaveRoomsDto {

    private Long memberId;

    private List<ChatRoomDto> chatRoomDtos;
}
