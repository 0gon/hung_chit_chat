package com.kafka.kafkachat.chat.dto.response;

import com.kafka.kafkachat.chat.dto.UserChatRoomDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ResponseGetChatRoomsDto {

    private Long memberId;
    private Long chatRoomId;
    private String chatRoomName;
    private List<UserChatRoomDto> userChatRoomDtoList;
}
