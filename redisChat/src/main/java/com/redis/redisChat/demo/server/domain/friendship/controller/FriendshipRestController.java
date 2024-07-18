package com.redis.redisChat.demo.server.domain.friendship.controller;

import com.redis.redisChat.demo.comm.CookieHandler;
import com.redis.redisChat.demo.server.domain.friendship.dto.FriendDto;
import com.redis.redisChat.demo.server.domain.friendship.service.FriendshipService;
import com.redis.redisChat.demo.server.domain.member.dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



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
