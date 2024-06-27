package com.redis.redisChat.demo.member.controller;

import com.redis.redisChat.demo.member.dto.MemberDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

    @PostMapping("/api/login")
    public void login(MemberDTO memberDTO) {

    }

}
