package com.redis.redisChat.demo.webSocket;

import java.util.List;

import com.redis.redisChat.demo.comm.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);
    private final SessionInfoService service;
    private final int port;

    public WebSocketHandler(SessionInfoService service, WebServerApplicationContext webServerAppContext) {
        this.service = service;
        this.port = webServerAppContext.getWebServer().getPort();
    }


    /* 클라이언트가 소켓 연결시 동작 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String memberId = (String) session.getAttributes().get("member_id");
        String internalIP = (String) session.getAttributes().get("internal_ip");
        String externalIP = (String) session.getAttributes().get("external_ip");

        SessionInfo sessionInfo = new SessionInfo(null, memberId, internalIP, externalIP, port, session.getId());
        service.save(sessionInfo);
        log.info("소켓연결. 세션: {}", sessionInfo);


    }

    /* 클라이언트로부터 메시지 수신시 동작 */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        log.info("수신메세지: {}", msg);
        JSONObject jsonObject = Util.jsonToObjectParser(msg);
        JSONArray jsonArray = (JSONArray) jsonObject.get("members");
        for (Object object: jsonArray) {
            String memberId = (String) object;

        }


//        for (String key : sessionMap.keySet()) {
//            WebSocketSession wss = sessionMap.get(key);
//            wss.sendMessage(new TextMessage(msg));
//        }
    }

    /* 클라이언트가 소켓 종료시 동작 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("소켓연결 종료. 세션: {}", session.getId());
//        sessionMap.remove(session.getId());
//        for (String key : sessionMap.keySet()) {
//            WebSocketSession wss = sessionMap.get(key);
//
//        }

//        List<SessionInfo> sessionInfos = service.findByMemberId(memberId);
//        if (sessionInfos.isEmpty()) {
//            SessionInfo sessionInfo = new SessionInfo(null, memberId, internalIP, externalIP, port, session.getId());
//            service.save(sessionInfo);
//            log.info("세션 정보: {}", sessionInfo);
//        } else {
//
//        }

    }


}