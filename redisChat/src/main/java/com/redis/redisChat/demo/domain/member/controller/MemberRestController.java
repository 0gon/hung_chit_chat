package com.redis.redisChat.demo.domain.member.controller;

import com.redis.redisChat.demo.domain.member.dto.MemberDTO;
import com.redis.redisChat.demo.domain.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService service;

    @PostMapping("/api/signin")
    public void signin(MemberDTO memberDTO, HttpServletResponse response) throws URISyntaxException, IOException {

        log.info("user signin: {}", memberDTO.getId());

        // 토큰으로 대체하기
        Cookie cookie1 = new Cookie("member_id", memberDTO.getId());
        cookie1.setPath("/");
        response.addCookie(cookie1);

        Cookie cookie2 = new Cookie("member_nickName", memberDTO.getNickName());
        cookie2.setPath("/");
        response.addCookie(cookie2);
        response.sendRedirect("/chat");
    }

    @PostMapping("/api/signup")
    public void signup(MemberDTO memberDTO, HttpServletResponse response) throws URISyntaxException, IOException {
        String memberId = service.save(memberDTO);
        log.info("user signup: {}", memberId);

        response.sendRedirect("/login");
    }

}
