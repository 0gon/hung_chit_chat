package com.redis.redisChat.demo.server.domain.member.repository;

import com.redis.redisChat.demo.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}