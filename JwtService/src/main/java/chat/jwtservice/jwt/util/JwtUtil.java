package chat.jwtservice.jwt.util;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    /***
     * Access 토큰 생성
     * @param email 유저 ID
     * @return 생성된 JWT token 문자열 반환
     */
    public String generateAccessToken(String email, String memberId) {
        return JWT.create()
                .withClaim("member_id", memberId)
                .withSubject("access")                                                      // 토큰 주체, token_type
                .withIssuedAt(new Date())                                                   // 토큰 발행 시간, 현재시간
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))       // 1 hours 1000 * 60 * 60
                .withClaim("email", email)                                            // claim 커스텀
                .withClaim("role", extractUserRole(memberId))
                .sign(Algorithm.HMAC256(SECRET_KEY));                                       // 서명, SECRET_KEY 를 HMAC256 알고리즘으로 변환
    }

    /**
     * MemberService 에 member Role 요청
     * @param memberId
     * @return String 타입 Role
     * */
    private String extractUserRole(String memberId) {

        RestTemplate restTemplate = new RestTemplate();

        URI uri = null;
        try {
            uri  = new URI("http://localhost:8081/members/auth/users/" + memberId);
        } catch (URISyntaxException e){
            throw new IllegalArgumentException(e.getMessage());
        }

        ResponseEntity<HashMap> response = restTemplate.getForEntity(uri, HashMap.class);

        if(response.getStatusCode().value() == 200 && response.getBody().get("role") != null) {
           return "ROLE_" + response.getBody().get("role").toString();
        }

        throw new IllegalArgumentException("Invalid user");

    }

    /***
     *  Refresh 토큰 생성
     * @param email 유저 ID
     * @return 생성된 JWT token 문자열 반환
     */
    public String generateRefreshToken(String email, String memberId) {

        return JWT.create()
                .withClaim("member_id", memberId)
                .withClaim("email", email)                                                     // claim 커스텀
                .withSubject("refresh")                                                              // 토큰 주체, token_type 추가
                .withIssuedAt(new Date())                                                            // 토큰 발행 시간, 현재시간
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 3))       // 3 day 1000 * 60 * 60 * 24 * 3
                .sign(Algorithm.HMAC256(SECRET_KEY));                                       // 서명, SECRET_KEY 를 HMAC256 알고리즘으로 변환
    }

    /**
     * 토큰 추출
     * @param token 토큰
     * @return 토큰에서 추출한 Subject
     * */
    public String extractSubject(String token) {
        return JWT.decode(token).getSubject();
    }

}
