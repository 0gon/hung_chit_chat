package com.kafka.kafkachat.chat.dto;

import com.kafka.kafkachat.chat.dto.response.ResponseGetChatRoomsDto;
import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.chat.entity.UserChatRoom;
import com.kafka.kafkachat.member.dto.MemberDto;
import com.kafka.kafkachat.member.entity.Member;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Getter
@Transactional
public class ChatConverter {

    /**
     * 채팅방 DTO -> 채팅방 entity 변환
     * */
    public static ChatRoom ChatRoomDtotoEntity(ChatRoomDto chatRoomDto) {
        return ChatRoom.builder()
                .id(chatRoomDto.getId())
                .name(chatRoomDto.getName())
                .build();
    }

    /**
     * 멤버 DTO -> 멤버 entity 로 변환
     * */
    public static Member MemberDtotoEntity(MemberDto memberDto) {
        return Member.builder()
                .id(memberDto.getId())
                .username(memberDto.getName())
                .build();
    }


}
