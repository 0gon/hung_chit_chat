package com.redis.redisChat.demo.domain.member.service;

import com.redis.redisChat.demo.domain.member.dto.MemberDTO;
import com.redis.redisChat.demo.domain.member.entity.Member;
import com.redis.redisChat.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repo;

    @Transactional
    public String save(MemberDTO memberDTO) {
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

    @Transactional
    public void test(String memberId, String changeNickName) {
        Member member = repo.findById(memberId).get();
        System.out.println("member: " + member);
        member.setNickName(changeNickName);
        System.out.println("member: " + member);
    }

}
