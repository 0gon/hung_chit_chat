package com.redis.redisChat.demo.webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionInfoService {

    private final SessionInfoRepository repository;

    public SessionInfo save(SessionInfo sessionInfo) {
        return repository.save(sessionInfo);
    }

}
