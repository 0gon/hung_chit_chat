package com.kafka.kafkachat.chat.repository;

import com.kafka.kafkachat.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
}
