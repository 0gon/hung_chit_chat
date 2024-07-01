package com.kafka.kafkachat.chat.dto;

import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.member.dto.MemberDto;
import com.kafka.kafkachat.member.entity.Member;
import lombok.Getter;

@Getter
public class ChatConverter {

    public static ChatRoom ChatRoomDtotoEntity(ChatRoomDto chatRoomDto) {
        return ChatRoom.builder()
                .id(chatRoomDto.getId())
                .name(chatRoomDto.getName())
                .build();
    }

    public static Member MemberDtotoEntity(MemberDto memberDto) {
        return Member.builder()
                .id(memberDto.getId())
                .username(memberDto.getName())
                .build();
    }
}
