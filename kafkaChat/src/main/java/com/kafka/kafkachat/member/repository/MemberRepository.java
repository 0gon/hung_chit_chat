package com.kafka.kafkachat.member.repository;

import com.kafka.kafkachat.member.dto.MemberDto;
import com.kafka.kafkachat.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(Member member);
}
