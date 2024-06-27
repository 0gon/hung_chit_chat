package com.redis.redisChat.demo.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPubService {

    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisPublisher redisPublisher;

    // 각 Channel 별 Listener
    private final RedisSubscribeListener redisSubscribeListener;


    /**
     * Channel 별 Message 전송
     * @param
     */
    public void pubMsgChannel(String channel ,MessageDTO message) {

        //1. 요청한 Channel 을 구독.
        redisMessageListenerContainer.addMessageListener(redisSubscribeListener, new ChannelTopic(channel));

        //2. Message 전송
        redisPublisher.publish(new ChannelTopic(channel), message);
    }

    /**
     * Channel 구독 취소
     * @param channel
     */
    public void cancelSubChannel(String channel) {
        redisMessageListenerContainer.removeMessageListener(redisSubscribeListener);
    }

}