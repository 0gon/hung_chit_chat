package com.memberservice.member.mock;

import com.memberservice.member.domain.entity.Member;
import com.memberservice.member.service.port.MemberRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMemberRepository implements MemberRepository {

    private final AtomicLong autoId = new AtomicLong(0);
    private final List<Member> members = new ArrayList<>();

    @Override
    public Member save(Member member) {
        if (member.getId() == null || member.getId() == 0) {
            Member newMember = Member.builder()
                    .id(autoId.getAndIncrement())
                    .memberId(member.getMemberId())
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .name(member.getName())
                    .gender(member.getGender())
                    .phoneNumber(member.getPhoneNumber())
                    .role(member.getRole())
                    .build();
            members.add(newMember);
            return newMember;
        } else {
            members.removeIf(item -> Objects.equals(item.getId(), member.getId()));
            members.add(member);
            return member;
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        return members.stream().filter(member -> member.getId().equals(id)).findAny();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return members.stream().filter(member -> member.getEmail().equals(email)).findAny();
    }

    @Override
    public Optional<Member> findByMemberId(String memberId) {
        return members.stream().filter(member -> member.getMemberId().equals(memberId)).findAny();
    }
}
