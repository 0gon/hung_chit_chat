package com.kafka.kafkachat.chat.service;

import com.kafka.kafkachat.chat.dto.ChatMessageDto;
import com.kafka.kafkachat.chat.entity.ChatMessage;
import com.kafka.kafkachat.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional
    public void saveMessage(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = chatMessageDto.toEntity(chatMessageDto);
        chatRepository.save(chatMessage);
    }

    public List<ChatMessageDto> getMessagesByRoomId(Long roomId) {

        List<ChatMessage> findMessage = chatRepository.findByRoomId(roomId);

        return findMessage.stream().map(ChatMessageDto::new).collect(Collectors.toList());

    }
}
