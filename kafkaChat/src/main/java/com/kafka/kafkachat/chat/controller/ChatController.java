package com.kafka.kafkachat.chat.controller;

import com.kafka.kafkachat.chat.dto.ChatMessageDto;
import com.kafka.kafkachat.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

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
}
