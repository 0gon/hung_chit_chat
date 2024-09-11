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
    public Mono<Boolean> validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            // .toString() -> .asString()  ==  toString 은 Claim 객체에 대한 설명을 반환 하기 때문에 ""결과값"" 이렇게 나올 수 있음, asString 으로 실제값 꺼내야함
            String email = jwt.getClaim("email").asString();
            String memberId = jwt.getClaim("member_id").asString();

            // 비동기적 체이닝 방식 사용 -> Spring cloud Gateway 에선 비동기 방식이 "필수", 동기 처리는 불가능 -> flatMap, then 메서드등 사용해서 처리 가능
            return checkMemberIdAndEmailFromDB(email, memberId)
                    .flatMap(isValid -> {
                        if(isValid){
                            log.info("isvalid : {}", isValid);
                            return Mono.just(true);
                        } else{
                            log.info("isvalid : {}", isValid);
                            return Mono.just(false);
                        }
                    });
        } catch (JWTVerificationException e) {
            log.error("token validation error : {} ", e.getMessage());
            return Mono.just(false);
        }
    }

    /**
     * email, memberId 로 체크
     * */
    protected Mono<Boolean> checkMemberIdAndEmailFromDB(String email, String memberId){

        String url = "http://localhost:8081/members/test/" + memberId;

        WebClient webClient = WebClient.create();

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ResponseMemberGatewayDto.class)
                .map(responseMemberGatewayDto -> email.equals(responseMemberGatewayDto.getEmail()))
                .onErrorResume(e -> {
                    // 오류가 발생한 경우 false 반환
                    log.error("Error occurred: " + e.getMessage());
                    return Mono.just(false);
                });

    }
}
