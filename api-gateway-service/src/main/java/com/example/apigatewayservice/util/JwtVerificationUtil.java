package com.example.apigatewayservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.apigatewayservice.dto.ResponseMemberGatewayDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@Component
public class JwtVerificationUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /**
     *  토큰 검증
     * @param token 토큰
     * @return boolean
     */
    public Boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            log.error("Token Verification error : {} ", e.getMessage());
            return false;
        } catch (Exception e){
            log.error("Token Validation error : {} ", e.getMessage());
            return false;
        }
    }
}
