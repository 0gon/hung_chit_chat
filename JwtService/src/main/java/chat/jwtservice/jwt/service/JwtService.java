package chat.jwtservice.jwt.service;

import chat.jwtservice.jwt.dto.request.RequestLoginTokenDto;
import chat.jwtservice.jwt.dto.request.RequestRefreshTokenDto;
import chat.jwtservice.jwt.dto.response.ResponseTokenDto;
import chat.jwtservice.jwt.entity.RefreshToken;
import chat.jwtservice.jwt.repository.JwtRepository;
import chat.jwtservice.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    public ResponseTokenDto generateToken(RequestLoginTokenDto requestLoginTokenDto) {

        String generateRefreshToken = jwtUtil.generateRefreshToken(requestLoginTokenDto.getEmail(), requestLoginTokenDto.getMemberId());

        String accessToken = jwtUtil.generateAccessToken(requestLoginTokenDto.getEmail(), requestLoginTokenDto.getMemberId(), requestLoginTokenDto.getRole());

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .refreshToken(generateRefreshToken)
                .memberId(requestLoginTokenDto.getMemberId())
                .email(requestLoginTokenDto.getEmail())
                .role(requestLoginTokenDto.getRole())
                .build();

        jwtRepository.save(refreshTokenEntity);

        return ResponseTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(generateRefreshToken)
                .build();
    }
    /**
     * 리프레쉬 토큰으로 엑세스 토큰 재발급
     * */
    @Transactional(readOnly = true)
    public ResponseTokenDto generateTokenByRefreshToken(RequestRefreshTokenDto requestRefreshTokenDto) {

        RefreshToken findRefreshToken = jwtRepository.findByRefreshToken(requestRefreshTokenDto.getRefreshToken()).orElseThrow(() -> new IllegalStateException("Refresh token not found"));

        if (LocalDateTime.now().isAfter(findRefreshToken.getExpiresAt())) {     // 만료 되었으면 exception
            throw new IllegalStateException("Refresh token Expired");
        }

        String accessToken = jwtUtil.generateAccessToken(findRefreshToken.getEmail(), findRefreshToken.getMemberId(), findRefreshToken.getRole());

        return ResponseTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(requestRefreshTokenDto.getRefreshToken())
                .build();
    }
}
