package com.redis.redisChat.demo.server.domain.member.service;

import com.redis.redisChat.demo.server.domain.member.dto.MemberDto;
import com.redis.redisChat.demo.server.domain.member.entity.Member;
import com.redis.redisChat.demo.server.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repo;

    @Transactional
    public String save(MemberDto memberDTO) {
        Member member = new Member();

        member.setId(memberDTO.getId());
        member.setNickName(memberDTO.getNickName());

        repo.save(member);
        return member.getId();
    }

    @Transactional
    public Optional<Member> find(String memberId) {
        return repo.findById(memberId);
    }


}
