package chat.jwtservice;

import chat.jwtservice.jwt.dto.request.RequestTokenDto;
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

        RequestTokenDto requestTokenDto = RequestTokenDto.builder()
                .email("ttt@ttt.com")
                .userId(1L)
                .build();

        try {
            ResponseTokenDto responseTokenDto = jwtService.generateToken(requestTokenDto);

            Assertions.assertThat(responseTokenDto).isNotNull();

            String username = jwtUtil.extractUsername(responseTokenDto.getAccessToken());
            Assertions.assertThat(username).isEqualTo("ttt@ttt.com");

            Optional<RefreshToken> byRefreshToken = jwtRepository.findByRefreshToken(responseTokenDto.getRefreshToken());
            if(byRefreshToken.isEmpty()){
                Assertions.fail("Not Saved Jwt Token in Database");
            } else{
                Assertions.assertThat(byRefreshToken.get().getUserId()).isEqualTo(1L);
                Assertions.assertThat(byRefreshToken.get().getRefreshToken()).isEqualTo(responseTokenDto.getRefreshToken());
                System.out.println("responseTokenDto = " + responseTokenDto.getRefreshToken());
            }

            //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJzdWIiOiJ0dHRAdHR0LmNvbSIsImlhdCI6MTcyNTc3MDM0MCwiZXhwIjoxNzI2MDI5NTQwLCJ0b2tlbl90eXBlIjoicmVmcmVzaCJ9.B7A1ftotJs-WX8mnU-NWV4A134eUsezH_fkQB2V1QS0

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("리프레쉬 토큰 테스트")
    @Test
    public void JwtServiceApplicationTests() throws Exception{


    }

}
