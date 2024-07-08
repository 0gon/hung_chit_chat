package com.redis.redisChat.demo.domain.friendship.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.redis.redisChat.demo.domain.friendship.service.FriendshipService;
import com.redis.redisChat.demo.domain.member.dto.MemberDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Slf4j
@RequiredArgsConstructor
public class FriendshipRestController {

    private final FriendshipService service;

    @PostMapping("/api/friend/addFriend")
    public ResponseEntity postMethodName(@RequestBody MemberDTO friend) {
        String test = service.addFriend(friend);
        return ResponseEntity.ok(test);
    }
    

}
