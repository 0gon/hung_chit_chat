package com.kafka.kafkachat.member.service;

import com.kafka.kafkachat.chat.dto.ChatRoomDto;
import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.member.dto.MemberDto;
import com.kafka.kafkachat.member.dto.MemberHaveRoomsDto;
import com.kafka.kafkachat.member.dto.UserChatRoomDto;
import com.kafka.kafkachat.member.entity.Member;
import com.kafka.kafkachat.member.repository.MemberQueryRepository;
import com.kafka.kafkachat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;


    @Transactional
    public MemberDto createUser(MemberDto memberDto) {
        Member member = Member.builder()
                .username(memberDto.getName())
                .password(memberDto.getPassword())
                .createAt(LocalDateTime.now())
                .gender(memberDto.getGender())
                .phoneNumber(memberDto.getPhoneNumber())
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
        Member findMember = memberRepository.findByUsername(memberDto.getName()).orElseThrow(() -> new IllegalArgumentException("USER_NOT_FOUND"));

        return MemberDto.builder()
                .id(findMember.getId())
                .name(findMember.getUsername())
                .build();
    }

    /**
     * N+1 문제 해결 -> Entity 조회 -> fetch join 사용
     * */
    public MemberHaveRoomsDto findRooms(Long userId){

        List<ChatRoom> chatRooms = memberQueryRepository.findRoomByMemberId(userId);

        return MemberHaveRoomsDto.builder()
                .memberId(userId)
                .chatRoomDtos(chatRooms.stream().map(ChatRoomDto::new).toList())
                .build();

    }
}
