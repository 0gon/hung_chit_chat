package com.memberservice.jwt;

import com.memberservice.dto.request.RequestLoginDto;
import com.memberservice.dto.response.ResponseLoginDto;
import com.memberservice.entity.Member;
import com.memberservice.repository.MemberRepository;
import com.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JwtService {

    private final JwtUtil jwtUtil;

    //private final RedisTemplate<String, Object> redisTemplate;

    private final MemberRepository memberRepository;

    @Transactional
    public ResponseLoginDto createToken(RequestLoginDto requestLoginDto){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestLoginDto.getEmail(), requestLoginDto.getPassword());

        authenticationToken.getAuthorities();

        Optional<Member> findMember = memberRepository.findByEmail(requestLoginDto.getEmail());

        return null;
    }
}
