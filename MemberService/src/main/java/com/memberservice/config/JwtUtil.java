package com.memberservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /**
     * 토큰 추출
     * @param token 토큰
     * @return 토큰에서 추출한 ID
     * */
    public String extractUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    /**
     * 토큰 추출
     * @param token 토큰
     * @return 토큰에서 추출한 user_id
     * */
    public Long extractUserId(String token) {
        return JWT.decode(token).getClaim("user_id").asLong();
    }
    /**
     * 토큰 추출
     * @param token 토큰
     * @return 토큰에서 추출한 타입 ex)access, refresh
     * */
    public String extractType(String token) {
        return JWT.decode(token).getClaim("token_type").toString();
    }

    /**
     * 토큰이 만료되었는지 확인
     * @param token 토큰
     * @return 토큰에서 추출한 만료시간
     * */
    public Boolean isTokenExpired(String token){

        final Date expricateion = JWT.decode(token).getExpiresAt();             // 만료시간 추출
        return expricateion.before(new Date());                                  // 만료되었으면 true, 아니면 false
    }

    /**
     *  토큰 검증
     * @param token 토큰
     * @return boolean
     */
    public boolean validateToken(String token, String email){
        try{
            // 이메일을 Subject 로 가지고 있는 토큰 추출
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .withSubject(email)
                    .build();

            // 토큰 디코딩
            DecodedJWT jwt = verifier.verify(token);

            // 토큰 시간 만료 검증
            if(jwt.getExpiresAt().before(new Date())){
                return false;
            }

            String tokenSubject = jwt.getSubject();
            return tokenSubject.equals(email);
        } catch(JWTVerificationException e){
            // Invalid Token
            //e.printStackTrace();
        }

        return false;
    }
}
