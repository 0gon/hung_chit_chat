package com.redis.redisChat.demo.front;

import java.util.List;
import java.util.Optional;

import com.redis.redisChat.demo.server.domain.member.entity.Member;
import com.redis.redisChat.demo.server.domain.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.redis.redisChat.demo.comm.CookieHandler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FrontController {

    private final MemberService memberService;

    @GetMapping("/chat")
    public String chat() {
        return "chat.html";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }

    @GetMapping("/app")
    public String roomList() {
        return "app.html";
    }

    @GetMapping("/popup/addFriend")
    public String addFriend() {
        return "/popup/addFriend.html";
    }

    @GetMapping("/popup/makeRoom")
    public String makeRoom() {
        return "/popup/makeRoom.html";
    }

    @GetMapping("/popup/room")
    public String room() {
        return "/popup/room.html";
    }

}
