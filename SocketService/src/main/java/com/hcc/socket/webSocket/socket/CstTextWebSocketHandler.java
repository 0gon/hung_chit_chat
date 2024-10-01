package com.hcc.socket.webSocket.socket;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcc.socket.Message;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


@Getter
@Slf4j
@Component
public class CstTextWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessionMap;

    public CstTextWebSocketHandler() {
        this.sessionMap = new ConcurrentHashMap<>();
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {

        try {
            Authentication authentication = (Authentication) session.getPrincipal();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String email = userDetails.getUsername();
            Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) userDetails.getAuthorities();

            if (email != null) {
                sessionMap.put(email, session);
                log.info("connection success email : {}", email);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("connection fail");
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String jsonMsg = message.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();
        Message msg = objectMapper.readValue(jsonMsg, Message.class);
        List<String> members = msg.getMembers();
        for (String memberId : members) {
            WebSocketSession wss = sessionMap.get(memberId);
            if (wss != null)
                wss.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    }


    private Map<String, String> parseCookies(String cookieHeader) {
        Map<String, String> cookieMap = new HashMap<>();
        if (cookieHeader != null) {
            String[] cookies = cookieHeader.split(";"); // 쿠키를 세미콜론으로 분리
            for (String cookie : cookies) {
                String[] keyValue = cookie.split("=", 2); // 키와 값을 분리
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    cookieMap.put(key, value);
                }
            }
        }
        return cookieMap;
    }

}