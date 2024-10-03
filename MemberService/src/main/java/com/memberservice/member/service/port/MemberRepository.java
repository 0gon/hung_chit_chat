package com.memberservice.member.service.port;

import com.memberservice.member.domain.entity.Member;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByMemberId(String memberId);
}
