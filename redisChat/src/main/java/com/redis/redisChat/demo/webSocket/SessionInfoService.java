package com.redis.redisChat.demo.webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SessionInfoService {

    private final SessionInfoRepository repository;

    public SessionInfo save(SessionInfo sessionInfo) {
        return repository.save(sessionInfo);
    }

    public List<SessionInfo> findByMemberId(String memberId) {
        return repository.findByMemberId(memberId);
    }

    public List<SessionInfo> findByMemberIdIn(List<String> memberIds) {
        return repository.findByMemberIdIn(memberIds);
    }

}
