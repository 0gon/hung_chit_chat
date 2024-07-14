package com.redis.redisChat.demo.domain.memberRoom.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRoomRepository extends JpaRepository<MemberRoom, Long> {
}
