package com.kafka.kafkachat.member.service;

import com.kafka.kafkachat.member.dto.MemberDto;
import com.kafka.kafkachat.member.entity.Member;
import com.kafka.kafkachat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public MemberDto createUser(MemberDto memberDto) {
        Member member = Member.builder()
                .username(memberDto.getName())
                .build();

        try {
            Member savedMember = memberRepository.save(member);
            return MemberDto.builder()
                    .id(savedMember.getId())
                    .name(savedMember.getUsername())
                    .build();
        } catch(Exception e){
            return null;
        }
    }

    public MemberDto findUser(MemberDto memberDto) {
        Member member = Member.builder()
                .username(memberDto.getName())
                .build();

        Member findMember = memberRepository.findByUsername(member).orElseThrow(() -> new IllegalArgumentException("USER_NOT_FOUND"));

        return MemberDto.builder()
                .id(findMember.getId())
                .name(findMember.getUsername())
                .build();
    }
}
