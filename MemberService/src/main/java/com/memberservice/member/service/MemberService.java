package com.memberservice.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memberservice.member.domain.Converter;
import com.memberservice.member.domain.dto.jackson.MemberView;
import com.memberservice.member.domain.dto.request.RequestLoginDto;
import com.memberservice.member.domain.dto.request.SignUpMemberDto;
import com.memberservice.member.domain.dto.response.ResponseMemberDto;
import com.memberservice.member.domain.dto.response.ResponseTokenDto;
import com.memberservice.member.domain.entity.Member;
import com.memberservice.member.domain.dto.jackson.Views;
import com.memberservice.member.service.port.IdentifierFactory;
import com.memberservice.member.service.port.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final IdentifierFactory identifierFactory;


    // 회원가입
    @Transactional
    public Member signUp(SignUpMemberDto signUpMemberDto) {

        // 비밀번호 encode
        signUpMemberDto.EncodePassword(passwordEncoder.encode(signUpMemberDto.getPassword()));
        Member member = Converter.RequestToEntity(signUpMemberDto, identifierFactory.generate());

        return memberJpaRepository.save(member);
    }

    // 로그인
    public ResponseTokenDto signIn(RequestLoginDto requestLoginDto) {

        Member member = memberJpaRepository.findByEmail(requestLoginDto.getEmail()).orElseThrow();
        if (passwordEncoder.matches(requestLoginDto.getPassword(), member.getPassword())) {
            MemberView memberView = Converter.MemberToView(member);
            String jsonBody = null;
            try {
                jsonBody = objectMapper.writerWithView(Views.MemberIdAndEmail.class).writeValueAsString(memberView);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            String uri = "http://localhost:8089/jwt/login"; // jwt service


            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = null;
            try {
                request = HttpRequest.newBuilder()
                        .uri(new URI(uri))
                        .header("Content-Type", "application/json") // 요청 헤더 설정
                        .POST(BodyPublishers.ofString(jsonBody)) // 본문 추가
                        .build();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                String body = response.body();

                return objectMapper.readValue(body, ResponseTokenDto.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new RuntimeException("no match password");
        }
    }



    public ResponseMemberDto getMemberByMemberId(String memberId){
        Member findMember = memberJpaRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalStateException("USER NOT FOUND"));

        return ResponseMemberDto.builder()
                .email(findMember.getEmail())
                .memberId(findMember.getMemberId())
                .role(findMember.getRole())
                .build();
    }
}
