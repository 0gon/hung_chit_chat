package com.redis.redisChat.demo.front;

import java.net.CookieHandler;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.redis.redisChat.demo.domain.member.entity.Member;
import com.redis.redisChat.demo.domain.member.service.MemberService;
import com.redis.redisChat.demo.domain.memberRoom.entity.MemberRoom;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/roomList")
    public String roomList(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String memberId = CookieHandler.getCookieValue(cookies, "member_id");
        System.out.println("memberId: " + memberId);

        Optional<Member> optionalMember = memberService.find(memberId);
        Member member = null;

        if (optionalMember.isPresent()) {
            member = optionalMember.get();
            System.out.println("member: " + member);
        }

        List<MemberRoom> memberRooms = member.getMemberRooms();
        System.out.println("memberRooms: " + memberRooms);
        for (MemberRoom memberRoom : memberRooms) {
            System.out.println("memberRoom: " + memberRoom);
        }

        return "roomList.html";
    }

    private String getCookieValue(Cookie[] cookies, String name) {
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals(name)) {
                return cookie.getValue();
            }
        }

        return null;
    }

    @GetMapping("/popup/{popupName}")
    public String addFriend(@PathVariable(value = "popupName") String popupName) {
        return "/popup/" + popupName;
    }

}
