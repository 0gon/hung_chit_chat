package com.example.apigatewayservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@Component
public class JwtVerificationUtil {

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
    public boolean validateToken(String token){
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            String email = jwt.getClaim("email").toString();
            String memberId = jwt.getClaim("member_id").toString();

            return this.checkMemberIdAndEmailFromDB(email, memberId);
        } catch(JWTVerificationException e){
            log.error("token validation error : {} ", e.getMessage());
        }

        return false;
    }

    /**
     * email, memberId 로 체크
     * */
    protected boolean checkMemberIdAndEmailFromDB(String email, String memberId){

        WebClient webClient = WebClient.create();

        String url = "http://localhost:8081/members/" + memberId;

        Mono<String> response = webClient.get().uri(url)
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe( result -> {
            System.out.println("result = " + result);
        });

        return true;

    }
}
