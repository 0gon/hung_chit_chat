package com.kafka.kafkachat.chat.repository;

import com.kafka.kafkachat.chat.entity.ChatRoom;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    // Redis 캐시 적용
    @Cacheable(value = "chatRooms", key = "#id")
    Optional<ChatRoom> findById(Long id);

}
