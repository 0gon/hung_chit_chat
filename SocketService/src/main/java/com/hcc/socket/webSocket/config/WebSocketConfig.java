package com.hcc.socket.webSocket.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {


    public final String PATH = "/ws";
    private final TextWebSocketHandler cstTextWebSocketHandler;
    private final HandshakeInterceptor handshakeInterceptor;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(cstTextWebSocketHandler, PATH)
                .addInterceptors(handshakeInterceptor)
                .setAllowedOriginPatterns("*");
    }
}
