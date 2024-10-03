package com.hcc.socket.webSocket.comm.jwt;

import com.auth0.jwt.JWT;
import com.hcc.socket.webSocket.comm.jwt.port.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


public class DecodedAuth0 implements DecodedJWT {

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

    private String extractClaim(String token, String claim) {
        return JWT.decode(token).getClaim(claim).asString();
    }
}
