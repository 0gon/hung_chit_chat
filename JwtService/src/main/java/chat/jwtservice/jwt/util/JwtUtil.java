package chat.jwtservice.jwt.util;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

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

}
