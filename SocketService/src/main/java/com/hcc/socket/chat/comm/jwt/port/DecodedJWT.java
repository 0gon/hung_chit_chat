package com.hcc.socket.chat.comm.jwt.port;

import org.springframework.security.core.Authentication;

public interface DecodedJWT {

    Authentication extractAuthentication(String token);
}
