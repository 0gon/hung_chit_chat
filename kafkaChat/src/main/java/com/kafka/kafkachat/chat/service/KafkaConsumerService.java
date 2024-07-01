package com.kafka.kafkachat.chat.service;

import com.kafka.kafkachat.chat.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;

    // 임시 토픽 1개
    @KafkaListener(topics = "chat-room-A", groupId = "chat-room")
    public void listen(ChatMessageDto messageDto, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        try{

        // Kafka로부터 메시지를 받아 처리
        log.info("Received message from Kafka: " + messageDto.getMessage());
        log.info("GetId: " + messageDto.getChatRoomId());
        log.info("topic: " + topic);

        Long roomId = messageDto.getChatRoomId();

        ChatMessageDto chatMessageDto = ChatMessageDto.builder()
                .chatRoomId(roomId)
                .senderId(messageDto.getSenderId())
                .message(messageDto.getMessage())
                .build();

        messagingTemplate.convertAndSend("/queue/" + roomId, chatMessageDto);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
