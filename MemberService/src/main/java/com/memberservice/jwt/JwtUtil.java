package com.memberservice.jwt;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    // TODO :: 설정파일로 뺴야함
    private static final String SECRET_KEY = "KEYKEYKEY";

    /***S
     * Access 토큰 생성
     * @param email 유저 ID
     * @return 생성된 JWT token 문자열 반환
     */
    public String generateAccessToken(String email, Long userId) {
        return JWT.create()
                .withClaim("user_id", userId)
                .withSubject(email)                                                      // 토큰 주체, 사용자 이름
                .withIssuedAt(new Date())                                                   // 토큰 발행 시간, 현재시간
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))       // 1 hours 1000 * 60 * 60
                .withClaim("token_type", "access")                              // token_type 추가 , claim 커스텀
                .sign(Algorithm.HMAC256(SECRET_KEY));                                       // 서명, SECRET_KEY 를 HMAC256 알고리즘으로 변환
    }

    /***
     *  Refresh 토큰 생성
     * @param email 유저 ID
     * @return 생성된 JWT token 문자열 반환
     */
    public String generateRefreshToken(String email, Long userId) {

        return JWT.create()
                .withClaim("user_id", userId)
                .withSubject(email)                                                      // 토큰 주체, 사용자 이름
                .withIssuedAt(new Date())                                                   // 토큰 발행 시간, 현재시간
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 3))       // 3 day 1000 * 60 * 60 * 24 * 3
                .withClaim("token_type", "refresh")                         // token_type 추가 , claim 커스텀
                .sign(Algorithm.HMAC256(SECRET_KEY));                                       // 서명, SECRET_KEY 를 HMAC256 알고리즘으로 변환
    }

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
