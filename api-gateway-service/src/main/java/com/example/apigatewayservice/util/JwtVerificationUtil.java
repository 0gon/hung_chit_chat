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
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.net.URISyntaxException;
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
    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            // .toString() -> .asString()  ==  toString 은 Claim 객체에 대한 설명을 반환 하기 때문에 ""결과값"" 이렇게 나올 수 있음, asString 으로 실제값 꺼내야함
            String email = jwt.getClaim("email").asString();
            String memberId = jwt.getClaim("member_id").asString();

            return this.checkMemberIdAndEmailFromDB(email, memberId);
        } catch (JWTVerificationException e) {
            log.error("token validation error : {} ", e.getMessage());
            return false;
        }
    }

    /**
     * email, memberId 로 체크
     * */
    protected boolean checkMemberIdAndEmailFromDB(String email, String memberId){

        String url = "http://localhost:8081/members/" + memberId;

        WebClient webClient = WebClient.create();

        Mono<Boolean> booleanMono = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ResponseMemberGatewayDto.class)
                .map(responseMemberGatewayDto ->  {return email.equals(responseMemberGatewayDto.getEmail());}) // 성공적인 응답 시 true 반환
                .onErrorResume(e -> {
                    // 오류가 발생한 경우 false 반환
                    System.err.println("Error occurred: " + e.getMessage());
                    return Mono.just(false);
                });


        return false;

    }
}
