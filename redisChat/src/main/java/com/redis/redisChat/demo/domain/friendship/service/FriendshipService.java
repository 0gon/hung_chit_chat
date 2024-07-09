package com.redis.redisChat.demo.domain.friendship.service;

import com.redis.redisChat.demo.domain.friendship.entity.Friendship;
import com.redis.redisChat.demo.domain.friendship.exception.CustomNotFoundException;
import com.redis.redisChat.demo.domain.friendship.repository.FriendshipReopository;
import com.redis.redisChat.demo.domain.member.dto.MemberDTO;
import com.redis.redisChat.demo.domain.member.entity.Member;
import com.redis.redisChat.demo.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendshipService {

    private final MemberRepository memberRepository;
    private final FriendshipReopository friendshipReopository;

    @Transactional
    public String addFriend(MemberDTO friendDTO) {
        String friendId = friendDTO.getId();
        Member friend = null;
        try {
            friend = memberRepository.findById(friendId)
                    .orElseThrow(() -> new CustomNotFoundException("친구를 찾을 수 없습니다."));
        } catch (CustomNotFoundException e) {
            log.info("addFriend fail");
            return "addFriend fail";
        }

        Friendship friendship = new Friendship("asdf", friend.getId(), null, null);
        friendshipReopository.save(friendship);

        return "addFriend succes";
    }

    @Transactional
    public Optional<Member> find(String memberId) {
        return memberRepository.findById(memberId);
    }

}
