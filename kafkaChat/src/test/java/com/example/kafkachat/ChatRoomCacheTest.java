package com.example.kafkachat;

import com.kafka.kafkachat.KafkaChatApplication;
import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.chat.repository.ChatRoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = KafkaChatApplication.class)
@ExtendWith(SpringExtension.class)
@Transactional
public class ChatRoomCacheTest {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    CacheManager cacheManager;

    @Test
    public void testRedisCache() {
        // 캐시가 비어 있는 상태에서 처음 조회
        Optional<ChatRoom> chatRoom1 = chatRoomRepository.findById(1L);
        assertTrue(chatRoom1.isPresent());

        // 캐시에 저장된 상태에서 두 번째 조회
        Optional<ChatRoom> chatRoom2 = chatRoomRepository.findById(1L);
        assertTrue(chatRoom2.isPresent());

        // 두 번째 조회 결과는 캐시된 결과여야 합니다.
        assertEquals(chatRoom1.get(), chatRoom2.get());

        // 캐시 매니저를 통해 캐시가 있는지 확인
        assertNotNull(cacheManager.getCache("chatRooms").get(1L).get());
    }
}
