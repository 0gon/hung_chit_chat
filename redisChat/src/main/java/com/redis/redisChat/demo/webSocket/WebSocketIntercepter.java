package com.redis.redisChat.demo.webSocket;

import com.redis.redisChat.demo.comm.Util;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketIntercepter implements HandshakeInterceptor {


    /**
     * @return true: 핸드셰이크 진행 | false: 중단
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("웹소켓 인터셉터 지나가는중--------------------");

        HttpHeaders headers = request.getHeaders();
        Map<String, String> headersMap = headers.toSingleValueMap();
        Map<String, String> cookiesMap = Util.getCookieMap(headersMap);


        String memberId = cookiesMap.get("member_id");
        String internalIP = Util.getInternalIP(); // 내부 ip
        String externalIP = Util.getExternalIP(); // 외부 ip

        System.out.println("내, 외부 ip: " + internalIP + ", " + externalIP);


        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }


}
