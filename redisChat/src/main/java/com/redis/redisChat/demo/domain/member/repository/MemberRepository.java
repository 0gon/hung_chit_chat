package com.redis.redisChat.demo.domain.member.repository;

import com.redis.redisChat.demo.domain.member.entity.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public String save(Member member) {
        em.persist(member);
        return member.getId();
    }

}
