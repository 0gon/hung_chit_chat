package com.kafka.kafkachat.chat.service;

import com.kafka.kafkachat.chat.dto.ChatMessageDto;
import com.kafka.kafkachat.chat.dto.ChatRoomDto;
import com.kafka.kafkachat.chat.entity.ChatMessage;
import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.chat.repository.ChatRepository;
import com.kafka.kafkachat.chat.repository.ChatRoomRepository;
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

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public void saveMessage(ChatMessageDto chatMessageDto) {

        ChatRoom chatRoom = chatRoomRepository.findById(chatMessageDto.getChatRoomId()).orElseThrow(() -> new IllegalArgumentException("EMPTY ROOM"));

        ChatMessage chatMessage = ChatMessage.builder()
                .message(chatMessageDto.getMessage())
                .senderId(chatMessageDto.getSenderId())
                .chatRoom(chatRoom)
                .build();

        chatRepository.save(chatMessage);
    }

//    public List<ChatMessageDto> getMessagesByRoomId(Long roomId) {
//        List<ChatMessage> findMessage = chatRepository.findByRoomId(roomId);
//        return findMessage.stream().map(ChatMessageDto::new).collect(Collectors.toList());
//    }
}
