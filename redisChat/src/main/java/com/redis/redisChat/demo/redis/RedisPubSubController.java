package com.redis.redisChat.demo.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/redis/pubsub")
public class RedisPubSubController {

    private final RedisPubService redisPubService;

    @PostMapping("/send")
    public void sendMessage(@RequestParam(required = true) String channel, @RequestBody MessageDTO message) {
        log.info("Redis Pub MSG Channel = {}", channel);
        redisPubService.pubMsgChannel(channel, message);
    }
}
