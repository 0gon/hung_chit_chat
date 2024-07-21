package com.redis.redisChat.demo.webSocket;

import com.redis.redisChat.demo.comm.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * todo: 나중에 클라이언트 종료로 인한 핸드셰이크 종료 시 세션정보 삭제하는 기능 만들어야함.
     *  일정 시간마다 연결 시도해서 종료 시에 세션정보 삭제시키는 기능으로 만들면 될듯?
     */

    /**
     * @return true: 핸드셰이크 진행 | false: 중단
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String memberId = Util.getCookieValue(request, "member_id");
        String internalIP = Util.getInternalIP(); // 내부 ip
        String externalIP = Util.getExternalIP(); // 외부 ip / !!공공와이파이는 방화벽등 설정에 따라 외부ip 조회 불가함.

        attributes.put("member_id", memberId);
        attributes.put("internal_ip", internalIP);
        attributes.put("external_ip", externalIP);

        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }


}
