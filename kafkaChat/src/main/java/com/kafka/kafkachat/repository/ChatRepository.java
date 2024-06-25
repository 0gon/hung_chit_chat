package com.kafka.kafkachat.repository;

import com.kafka.kafkachat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomId(Long roomId);
}
