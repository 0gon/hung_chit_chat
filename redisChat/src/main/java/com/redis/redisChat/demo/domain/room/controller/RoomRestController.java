package com.redis.redisChat.demo.domain.room.controller;

import org.springframework.web.bind.annotation.RestController;

import com.redis.redisChat.demo.domain.friendship.dto.FriendDto;
import com.redis.redisChat.demo.domain.room.RoomService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class RoomRestController {

    private final RoomService service;
    
    @PostMapping("/api/room/makeRoom")
    public String makeRoom(@RequestBody List<String> friendIds) {
        service.makeRoom(friendIds);
        return "entity";
    }
    
}
