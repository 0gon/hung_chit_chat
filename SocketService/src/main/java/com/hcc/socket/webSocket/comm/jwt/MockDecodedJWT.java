package com.hcc.socket.webSocket.comm.jwt;

import com.auth0.jwt.JWT;
import com.hcc.socket.webSocket.comm.jwt.port.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class MockDecodedJWT implements DecodedJWT {

    public Authentication extractAuthentication(String token) {

        String email = extractClaim(token, "email");
        if (email == null) {
            return null;
        }

        String userRole = extractClaim(token, "role");

        UserDetails userDetails = User.withUsername(email)
                .password("")
                .authorities(userRole)
                .build();

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String extractClaim(String input, String key) {
        String[] pairs = input.split(","); // 콤마로 분리

        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2 && keyValue[0].trim().equals(key.trim())) {
                return keyValue[1].trim(); // 해당 키의 값 반환
            }
        }
        return null; // 키가 존재하지 않으면 null 반환
    }
}
