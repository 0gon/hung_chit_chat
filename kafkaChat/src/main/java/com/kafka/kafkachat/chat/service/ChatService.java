package com.kafka.kafkachat.chat.service;

import com.kafka.kafkachat.chat.dto.ChatConverter;
import com.kafka.kafkachat.chat.dto.ChatMessageDto;
import com.kafka.kafkachat.chat.dto.ChatRoomDto;
import com.kafka.kafkachat.chat.entity.ChatMessage;
import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.chat.repository.ChatRepository;
import com.kafka.kafkachat.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    private final ChatRoomRepository chatRoomRepository;

    private final RedisTemplate<String, Object> redisTemplate;
    private final AdminClient adminClient;

    @Transactional
    public void saveMessage(ChatMessageDto chatMessageDto) {

        Long roomId = chatMessageDto.getChatRoomId();

        ChatRoom chatRoom = checkCacheOrDb(roomId);

        ChatMessage chatMessage = ChatMessage.builder()
                .message(chatMessageDto.getMessage())
                .senderId(chatMessageDto.getSenderId())
                .chatRoom(chatRoom)
                .build();

        chatRepository.save(chatMessage);
    }

    /**
     * Redis 에서 채팅방 ID로 체크 후 없으면 객체 조회 후 레디스에 저장, 있으면 객체 반환
     *
     * @param chatRoomId 채팅 방 ID
     */
    private ChatRoom checkCacheOrDb(Long chatRoomId) {
        String key = "chatRoomId:" + chatRoomId;
        // 레디스에서 탐색
        ChatRoomDto chatRoomDto = (ChatRoomDto) redisTemplate.opsForValue().get(key);

        if (chatRoomDto == null) {
            ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new IllegalArgumentException("EMPTY ROOM"));

            // DTO 변환
            chatRoomDto = ChatRoomDto.builder()
                    .id(chatRoom.getId())
                    .name(chatRoom.getName())
                    .build();
            
            // 레디스에 저장
            redisTemplate.opsForValue().set(key, chatRoomDto);
            return chatRoom;
        }
        return ChatConverter.toEntity(chatRoomDto);
    }

    public void createKafkaTopic(Long id) {
        try{
            NewTopic newTopic = new NewTopic("chat-room", 1, (short)1);
            adminClient.createTopics(List.of(newTopic)).all().get();
        } catch(Exception e){
            throw new RuntimeException("Falied to create kafka topic: " + id, e);
        }
    }

//    public List<ChatMessageDto> getMessagesByRoomId(Long roomId) {
//        List<ChatMessage> findMessage = chatRepository.findByRoomId(roomId);
//        return findMessage.stream().map(ChatMessageDto::new).collect(Collectors.toList());
//    }
}
