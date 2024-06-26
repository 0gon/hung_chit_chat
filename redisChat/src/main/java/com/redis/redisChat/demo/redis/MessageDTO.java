package com.redis.redisChat.demo.redis;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message; // 메세지 내용
    private String sender; // 발신자
    private String roomId; // 타겟 채널

}
