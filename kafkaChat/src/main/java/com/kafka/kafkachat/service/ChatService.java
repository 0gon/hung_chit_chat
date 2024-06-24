package com.kafka.kafkachat.service;

import com.kafka.kafkachat.dto.ChatMessageDto;
import com.kafka.kafkachat.entity.ChatMessage;
import com.kafka.kafkachat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional
    public void saveMessage(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = chatMessageDto.toEntity(chatMessageDto);

        chatRepository.save(chatMessage);
    }
}
