package com.hcc.socket.webSocket.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Collection;
import java.util.Map;

@Slf4j
@Component
public class HandshakeInterceptorImpl implements HandshakeInterceptor {


    /**
     * @return true: 핸드셰이크 진행 | false: 중단
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        Authentication authentication = (Authentication) request.getPrincipal();
        if (authentication == null) return false;
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        String email = userDetails.getUsername();
//        Collection<GrantedAuthority> autorities = (Collection<GrantedAuthority>) userDetails.getAuthorities();
        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }


}
