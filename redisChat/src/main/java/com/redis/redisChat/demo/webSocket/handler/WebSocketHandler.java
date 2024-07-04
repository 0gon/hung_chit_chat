package com.redis.redisChat.demo.webSocket.handler;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

    Map<String, WebSocketSession> sessionMap = new HashMap<>(); // 웹소켓 세션을 담아둘 맵

    /* 클라이언트가 소켓 연결시 동작 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("\n소켓연결. 세션: {}", session.getId());
        sessionMap.put(session.getId(), session);
        for (String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
        }
    }

    /* 클라이언트로부터 메시지 수신시 동작 */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        log.info("\n수신메세지: {}", msg);
        for (String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);
            wss.sendMessage(new TextMessage(msg));
        }
    }

    /* 클라이언트가 소켓 종료시 동작 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("\n소켓연결 종료. 세션: {}", session.getId());
        sessionMap.remove(session.getId());
        for (String key : sessionMap.keySet()) {
            WebSocketSession wss = sessionMap.get(key);

        }
    }

    /**
     * JSON 형태의 문자열을 JSONObejct로 파싱
     */
    private static JSONObject jsonToObjectParser(String jsonStr) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        obj = (JSONObject) parser.parse(jsonStr);
        return obj;
    }
}