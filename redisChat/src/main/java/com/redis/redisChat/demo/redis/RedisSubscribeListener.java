package com.redis.redisChat.demo.redis;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSubscribeListener implements MessageListener {

    private final RedisTemplate<String, Object> template;
    private final ObjectMapper objectMapper;


    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = template.getStringSerializer().deserialize(message.getBody());

            MessageDTO messageDTO = objectMapper.readValue(publishMessage, MessageDTO.class);

            log.info("Redis Subscribe Channel: {}", messageDTO.getRoomId());
            log.info("Redis SUB Message: {}", publishMessage);



        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
