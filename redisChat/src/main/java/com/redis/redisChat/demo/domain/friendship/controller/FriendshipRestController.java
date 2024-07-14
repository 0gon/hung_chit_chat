package com.redis.redisChat.demo.domain.friendship.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.redis.redisChat.demo.comm.CookieHandler;
import com.redis.redisChat.demo.domain.friendship.dto.FriendDto;
import com.redis.redisChat.demo.domain.friendship.service.FriendshipService;
import com.redis.redisChat.demo.domain.member.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Slf4j
@RequiredArgsConstructor
public class FriendshipRestController {

    private final FriendshipService service;

    @PostMapping("/api/friend/addFriend")
    public ResponseEntity<String> postMethodName(@RequestBody MemberDto friend) {
        String result = service.addFriend(friend);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/friendship/getFriends")
    public ResponseEntity getFriends(HttpServletRequest request) {
        String memberId = CookieHandler.getCookieValue(request, "member_id");
        List<FriendDto> friends = service.getFriends(memberId);
        return ResponseEntity.ok(friends);
    }

    
    
    

}
