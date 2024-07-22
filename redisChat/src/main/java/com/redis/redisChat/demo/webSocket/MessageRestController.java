package com.redis.redisChat.demo.webSocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageRestController {

    private final WebSocketHandler webSocketHandler;

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> payload) {
        String sessionId = payload.get("sessionId");
        String message = payload.get("message");
        log.info("http 메세지 수신------------------- msg: {}", message);

        WebSocketSession session = webSocketHandler.getSessions().get(sessionId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
                return ResponseEntity.ok("Message sent");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Failed to send message");
            }
        } else {
            return ResponseEntity.status(404).body("Session not found or closed");
        }
    }
}
