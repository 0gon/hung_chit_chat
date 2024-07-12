package com.kafka.kafkachat.member.repository;

import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.member.dto.MemberDto;
import com.kafka.kafkachat.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String userName);

}
