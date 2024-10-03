package com.hcc.socket.webSocket.config;

import com.hcc.socket.webSocket.comm.jwt.JWTHolder;
import com.hcc.socket.webSocket.comm.jwt.MockDecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ValidJWTFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authorizationHeader = request.getHeader("Authorization");
        JWTHolder JWTHolder = new JWTHolder(authorizationHeader, new MockDecodedJWT());


        if (!JWTHolder.existToken()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {


            Authentication authentication = JWTHolder.extractAuthentication();
            if (authentication == null && SecurityContextHolder.getContext().getAuthentication() != null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request, response);
    }

}
