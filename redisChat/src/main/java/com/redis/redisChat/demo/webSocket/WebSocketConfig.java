package com.redis.redisChat.demo.webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer{

    private final WebSocketHandler webSocketHandler;
    private final WebSocketInterceptor webSocketIntercepter;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws_chating")
                .addInterceptors(webSocketIntercepter);
//                .withSockJS();
//             .setAllowedOriginPatterns("*")
    }


    
}
