package com.memberservice;

import com.memberservice.dto.request.SignUpMemberDto;
import com.memberservice.entity.Gender;
import com.memberservice.entity.Member;
import com.memberservice.entity.Role;
import com.memberservice.repository.MemberRepository;
import com.memberservice.service.MemberService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class MemberServiceApplicationTests {

    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSave() {

        SignUpMemberDto signUpMemberDto = SignUpMemberDto.builder()
                .email("aaaa@aaaa.com")
                .password("asdf")
                .gender(Gender.MALE)
                .phoneNumber("01000001111")
                .role(Role.USER)
                .build();

        memberService.save(signUpMemberDto);

        Member member = memberRepository.findById(1L).get();

        Assertions.assertEquals(member.getEmail(), "aaaa@aaaa.com");

        System.out.println("member = " + member);


    }

}
