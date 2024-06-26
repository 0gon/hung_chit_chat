package com.redis.redisChat.demo.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisher {

    private final RedisTemplate<String, Object> template;

    public RedisPublisher(RedisTemplate<String, Object> template) {
        this.template = template;
    }



    public void publish(ChannelTopic topic, MessageDTO dto) {
        template.convertAndSend(topic.getTopic(), dto);
    }

    public void publish(ChannelTopic topic, String data) {
        template.convertAndSend(topic.getTopic(), data);
    }
}
