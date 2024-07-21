package com.redis.redisChat.demo.webSocket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionInfoRepository extends JpaRepository<SessionInfo, Long> {

    List<SessionInfo> findByMemberId(String memberId);

    List<SessionInfo> findByMemberIdIn(List<String> memberIds);
}
