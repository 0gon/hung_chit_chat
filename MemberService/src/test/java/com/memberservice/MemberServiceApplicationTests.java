package com.memberservice;

import com.memberservice.member.model.dto.request.SignUpMemberDto;
import com.memberservice.member.model.entity.Gender;
import com.memberservice.member.model.entity.Member;
import com.memberservice.member.model.entity.Role;
import com.memberservice.member.repository.MemberRepository;
import com.memberservice.member.service.MemberService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class MemberServiceApplicationTests {

    private final MemberService memberService;
    private final EntityManager em;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceApplicationTests(MemberService memberService, EntityManager em, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.em = em;
        this.memberRepository = memberRepository;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testSignUp() {

        SignUpMemberDto signUpMemberDto = SignUpMemberDto.builder()
                .email("aaaa@aaaa.com")
                .password("asdf")
                .gender(Gender.MALE)
                .phoneNumber("01000001111")
                .role(Role.USER)
                .build();

        Member savedMember = memberService.signUp(signUpMemberDto);

        Member member = memberRepository.findById(savedMember.getId()).get();

        Assertions.assertEquals(member.getEmail(), "aaaa@aaaa.com");

        System.out.println("member = " + member);


    }

}
