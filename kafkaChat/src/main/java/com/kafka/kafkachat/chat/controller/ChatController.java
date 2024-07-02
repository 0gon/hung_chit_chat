package com.kafka.kafkachat.chat.controller;

import com.kafka.kafkachat.chat.dto.*;
import com.kafka.kafkachat.chat.service.ChatService;
import com.kafka.kafkachat.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/chat")
    public String connectChat() {
        return "chat";
    }

    /**
     * 메시지 생성
     * */
    // /app/message -> /message
    @MessageMapping("/message")
    public void sendMessage(@Payload ChatMessageDto chatMessageDto) {
        log.info("Received message: " + chatMessageDto.getMessage());
        chatMessageDto.setTimestamp(LocalDateTime.now());
        chatService.saveMessage(chatMessageDto);

        // kafka로 메시지 전송
        kafkaTemplate.send("chat-room-A", chatMessageDto);
    }

    @GetMapping("/getMessage/{roomId}")
    public ResponseEntity<List<ChatMessageResponseDto>> subscribe(@PathVariable Long roomId) {
        return ResponseEntity.ok(chatService.getMessagesByRoomId(roomId));
    }

    @PostMapping("/rooms")
    public ResponseEntity<ResponseChatCreatedDto> createRoom(@RequestBody ChatCreatedDto chatCreatedDto){
        ResponseChatCreatedDto room = chatService.createRoom(chatCreatedDto);
        return ResponseEntity.ok().body(room);
    }
}
