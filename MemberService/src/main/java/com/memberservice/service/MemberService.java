package com.memberservice.service;

import com.memberservice.converter.Converter;
import com.memberservice.dto.request.SignUpMemberDto;
import com.memberservice.entity.Member;
import com.memberservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
}
