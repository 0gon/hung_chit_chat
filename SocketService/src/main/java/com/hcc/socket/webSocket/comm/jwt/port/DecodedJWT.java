package com.hcc.socket.webSocket.comm.jwt.port;

import org.springframework.security.core.Authentication;

public interface DecodedJWT {

    Authentication extractAuthentication(String token);
}
