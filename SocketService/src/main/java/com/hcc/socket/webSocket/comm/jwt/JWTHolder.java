package com.hcc.socket.webSocket.comm.jwt;

import com.hcc.socket.webSocket.comm.jwt.port.DecodedJWT;
import org.springframework.security.core.Authentication;

public class JWTHolder {

    private final String authorizationHeader;
    private final DecodedJWT decodedJWT;

    public JWTHolder(String authorizationHeader) {
        this.authorizationHeader = authorizationHeader;
        this.decodedJWT = new DecodedAuth0();
    }

    public JWTHolder(String authorizationHeader, DecodedJWT decodedJWT) {
        this.authorizationHeader = authorizationHeader;
        this.decodedJWT = decodedJWT;
    }


    public boolean existToken() {
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }


    public String getToken() {
        return authorizationHeader.substring(7);
    }


    public Authentication extractAuthentication() {
        String token = getToken();
        return decodedJWT.extractAuthentication(token);
    }

}
