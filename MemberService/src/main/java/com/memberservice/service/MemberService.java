package com.memberservice.service;

import com.memberservice.converter.Converter;
import com.memberservice.dto.request.SignUpMemberDto;
import com.memberservice.dto.response.ResponseMemberGatewayDto;
import com.memberservice.entity.Member;
import com.memberservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // todo: 만약에 memeber 바로 반환하는거 보기 싫으면 dto 만드셈
    @Transactional
    public Member save(SignUpMemberDto signUpMemberDto) {

        // 비밀번호 encode
        signUpMemberDto.EncodePassword(passwordEncoder.encode(signUpMemberDto.getPassword()));

        Member member = Converter.RequestToEntity(signUpMemberDto);
        return memberRepository.save(member);
    }

    /**
     * API GATEWAY SERVICE 요청 시 id, memberId, email 반환
     * @return type ResponseMemberGatewayDto
     * */
    public ResponseMemberGatewayDto retrieveMemberByMemberId(String memberId) {

        log.info("Retrieving member by member id {}", memberId);

        return ResponseMemberGatewayDto.builder()
                .email("ttt@ttt.com")
                .memberId("asdgbosdiv")
                .build();

//        Member findOptionalMember = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("MEMBER NOT FOUND"));
//
//        return ResponseMemberGatewayDto.builder()
//                .id(findOptionalMember.getId())
//                .email(findOptionalMember.getEmail())
//                .memberId(findOptionalMember.getMemberId())
//                .build();
    }
}
