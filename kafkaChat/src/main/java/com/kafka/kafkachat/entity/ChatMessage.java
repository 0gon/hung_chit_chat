package com.kafka.kafkachat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime sendDate;

    private Long senderId;

    private String message;

    private Long roomId;

    @Builder
    public ChatMessage(LocalDateTime sendDate, Long senderId, String message, Long roomId) {
        this.sendDate = sendDate;
        this.senderId = senderId;
        this.message = message;
        this.roomId = roomId;
    }
}
