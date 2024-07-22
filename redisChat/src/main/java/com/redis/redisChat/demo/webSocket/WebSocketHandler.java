package com.redis.redisChat.demo.webSocket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.redis.redisChat.demo.comm.Util;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Getter
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final SessionInfoService service;
    private final int port;
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

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
        SessionInfo saveSessionInfo = service.save(sessionInfo);
        Long sessionInfoId = saveSessionInfo.getId();
        session.getAttributes().put("sessionInfoId", sessionInfoId);

        log.info("소켓연결. 세션: {}", sessionInfo);

        sessions.put(session.getId(), session);

    }

    /* 클라이언트로부터 메시지 수신시 동작 */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메세지 형변환
        String msg = message.getPayload();
        log.info("수신메세지: {}", msg);
        JSONObject jsonObject = Util.jsonToObjectParser(msg);

        // 메세지 수신자들 세션 정보 확인
        JSONArray jsonArray = (JSONArray) jsonObject.get("members");
        List<String> memberIds = new ArrayList<>();
        for (Object object: jsonArray) {
            String memberId = (String) object;
            memberIds.add(memberId);
        }
        List<SessionInfo> sessionInfos = service.findByMemberIdIn(memberIds);

        // 메세지 전송
        RestTemplate restTemplate = new RestTemplate();
        for (SessionInfo sessionInfo : sessionInfos) {
            String url = "http://" + sessionInfo.getInternalIp() + ":" + sessionInfo.getPort() + "/send-message";
            JSONObject payload = new JSONObject();
            payload.put("sessionId", sessionInfo.getSessionId());
            payload.put("message", msg);

            log.info("타 세션으로 보내는 페이로드 문자열: {}",payload.toString());

            // HTTP POST 요청
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(payload.toString(), headers);

                restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            } catch (Exception e) {
                log.error("메시지 전송 실패: " + e.getMessage());
            }
        }
    }

    /* 클라이언트가 소켓 종료시 동작 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long sessionInfoId = (Long) session.getAttributes().get("sessionInfoId");
        service. // last todo: 삭제코드 만들기
        sessions.remove(session.getId(), session);
        log.info("소켓연결 종료. 세션: {}", session.getId());
    }


}