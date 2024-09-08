package com.hcc.wss;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    public final String PATH = "/ws";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), PATH)
                .addInterceptors(webSocketInterceptor())
                .setAllowedOriginPatterns("*");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new CustomWSHandler();
    }

    @Bean
    public HandshakeInterceptor webSocketInterceptor() {
        return new WebSocketInterceptor();
    }


}
