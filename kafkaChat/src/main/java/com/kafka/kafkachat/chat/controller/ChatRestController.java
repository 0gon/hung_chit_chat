package com.kafka.kafkachat.chat.controller;


import com.kafka.kafkachat.chat.dto.ChatCreatedDto;
import com.kafka.kafkachat.chat.dto.ChatMessageResponseDto;
import com.kafka.kafkachat.chat.dto.response.ResponseChatCreatedDto;
import com.kafka.kafkachat.chat.dto.response.ResponseGetChatRoomsDto;
import com.kafka.kafkachat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatRestController {

    private final ChatService chatService;

    @GetMapping("/message/{roomId}")
    public ResponseEntity<List<ChatMessageResponseDto>> subscribe(@PathVariable Long roomId) {
        return ResponseEntity.ok(chatService.getMessagesByRoomId(roomId));
    }

    @PostMapping("/rooms")
    public ResponseEntity<ResponseChatCreatedDto> createRoom(@RequestBody ChatCreatedDto chatCreatedDto){
        ResponseChatCreatedDto room = chatService.createRoom(chatCreatedDto);
        return ResponseEntity.ok().body(room);
    }

}
