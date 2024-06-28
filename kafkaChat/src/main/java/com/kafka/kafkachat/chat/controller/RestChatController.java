package com.kafka.kafkachat.chat.controller;

import com.kafka.kafkachat.chat.dto.ChatRoomDto;
import com.kafka.kafkachat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class RestChatController {

    private final ChatService chatService;

    @PostMapping("/api/createChatRoom")
    public ResponseEntity<String> createChatRoom(@RequestBody ChatRoomDto chatRoomDto) {
        chatService.createKafkaTopic(chatRoomDto.getId());

        return ResponseEntity.ok().body("ok");
    }
}
