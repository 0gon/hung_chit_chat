package com.kafka.kafkachat.chat.controller;

import com.kafka.kafkachat.chat.dto.ChatMessageDto;
import com.kafka.kafkachat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

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
        chatService.saveMessage(chatMessageDto);
        // SimpleMessingTemplate -> Spirng WebSocket
        //messagingTemplate.convertAndSend("/queue/" + chatMessageDto.getRoomId(), chatMessageDto);


    }

    @GetMapping("/getMessage/{roomId}")
    public ResponseEntity<List<ChatMessageDto>> subscribe(@PathVariable Long roomId) {
        return ResponseEntity.ok(chatService.getMessagesByRoomId(roomId));
    }
}
