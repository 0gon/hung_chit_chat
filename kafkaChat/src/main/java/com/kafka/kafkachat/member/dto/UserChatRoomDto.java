package com.kafka.kafkachat.member.dto;

import com.kafka.kafkachat.chat.dto.ChatRoomDto;
import com.kafka.kafkachat.chat.entity.UserChatRoom;
import lombok.*;

@Getter
@NoArgsConstructor
public class UserChatRoomDto {

    private ChatRoomDto chatRoomDto;


    public UserChatRoomDto(UserChatRoom userChatRoom) {
        this.chatRoomDto = ChatRoomDto.builder()
                .id(userChatRoom.getId())
                .name(userChatRoom.getChatRoom().getName())
                .build();
    }
}
