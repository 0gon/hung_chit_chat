package chat.jwtservice;

import chat.jwtservice.jwt.dto.request.RequestLoginTokenDto;
import chat.jwtservice.jwt.dto.response.ResponseTokenDto;
import chat.jwtservice.jwt.entity.RefreshToken;
import chat.jwtservice.jwt.repository.JwtRepository;
import chat.jwtservice.jwt.service.JwtService;
import chat.jwtservice.jwt.util.JwtUtil;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional(readOnly = true)
class JwtServiceApplicationTests {

    @Autowired EntityManager em;

    @Autowired JwtService jwtService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired JwtRepository jwtRepository;

    @Test
    void contextLoads() {
    }

    @DisplayName("로그인 테스트")
    @Test
    @Transactional
    @Rollback(false)
    public void loginTest(){

        RequestLoginTokenDto requestTokenDto = RequestLoginTokenDto.builder()
                .email("ttt@ttt.com")
                .memberId("asdgbosdiv")
                .build();

        try {
            ResponseTokenDto responseTokenDto = jwtService.generateToken(requestTokenDto);

            Assertions.assertThat(responseTokenDto).isNotNull();

            String subject = jwtUtil.extractSubject(responseTokenDto.getAccessToken());
            Assertions.assertThat(subject).isEqualTo("access");

            Optional<RefreshToken> byRefreshToken = jwtRepository.findByRefreshToken(responseTokenDto.getRefreshToken());
            if(byRefreshToken.isEmpty()){
                Assertions.fail("Not Saved Jwt Token in Database");
            } else{
                Assertions.assertThat(byRefreshToken.get().getMemberId()).isEqualTo("asdgbosdiv");
                Assertions.assertThat(byRefreshToken.get().getRefreshToken()).isEqualTo(responseTokenDto.getRefreshToken());
                System.out.println("responseTokenDto = " + responseTokenDto.getRefreshToken());
                // access
                // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZW1iZXJfaWQiOiJhc2RnYm9zZGl2Iiwic3ViIjoiYWNjZXNzIiwiaWF0IjoxNzI1OTY5OTY4LCJleHAiOjE3MjU5NzM1NjgsImVtYWlsIjoidHR0QHR0dC5jb20ifQ.qSOSNTZwdjqAx_r8gAHszT-Tyrt3BULNWrKAVyDh8s8

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("토큰 생성")
    @Test
    public void JwtServiceApplicationTests() throws Exception{


    }

}
