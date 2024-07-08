package com.redis.redisChat.demo.domain.friendship.service;

import com.redis.redisChat.demo.domain.friendship.repository.FriendshipReopository;
import com.redis.redisChat.demo.domain.member.dto.MemberDTO;
import com.redis.redisChat.demo.domain.member.entity.Member;
import com.redis.redisChat.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final MemberRepository memberRepository;
    private final FriendshipReopository FriendshipReopository;

    @Transactional
    public String addFriend(MemberDTO friendDTO) {
        String friendId = friendDTO.getId();
        Member friend = memberRepository.findById(friendId).orElseThrow();
        System.out.println("friend: " + friend);
        return "hihi";
    }

    @Transactional
    public Optional<Member> find(String memberId) {
        return memberRepository.findById(memberId);
    }

}
