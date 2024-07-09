package com.redis.redisChat.demo.domain.friendship.exception;

public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException() {

    }

    public CustomNotFoundException(String message) {
        super(message);
    }
}
