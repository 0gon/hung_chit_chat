package chat.jwtservice.jwt.service;

import chat.jwtservice.jwt.dto.request.RequestTokenDto;
import chat.jwtservice.jwt.dto.response.ResponseTokenDto;
import chat.jwtservice.jwt.entity.RefreshToken;
import chat.jwtservice.jwt.repository.JwtRepository;
import chat.jwtservice.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtService {

    private final JwtUtil jwtUtil;

    private final JwtRepository jwtRepository;

    /**
     * 토큰 발급
     * */
    @Transactional
    public ResponseTokenDto generateToken(RequestTokenDto requestTokenDto) {

        String accessToken = jwtUtil.generateAccessToken(requestTokenDto.getEmail(), requestTokenDto.getUserId());
        String refreshToken = jwtUtil.generateRefreshToken(requestTokenDto.getEmail(), requestTokenDto.getUserId());

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .refreshToken(refreshToken)
                .userId(requestTokenDto.getUserId())
                .build();

        jwtRepository.save(refreshTokenEntity);

        return ResponseTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
