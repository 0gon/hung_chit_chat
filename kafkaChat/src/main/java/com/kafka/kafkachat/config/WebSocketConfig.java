package com.kafka.kafkachat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 접속 엔드포인트
        registry.addEndpoint("/chat").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 메시지 브로커 구성 "Prefixes" -> /app/message -> ChatController 에서 @MessageMapping("/message") 로 매핑됨
        registry.setApplicationDestinationPrefixes("/app");
        // /topic/message 를하면 클라이언트에서 /message 로 메시지를 받을 수 있다.
        registry.enableSimpleBroker("/queue", "/topic");
    }

}
