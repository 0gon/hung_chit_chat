package com.example.apigatewayservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
