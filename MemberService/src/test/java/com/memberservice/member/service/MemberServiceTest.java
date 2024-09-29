package com.memberservice.member.service;

import com.memberservice.config.MockAppConfig;
import com.memberservice.member.domain.dto.request.SignUpMemberDto;
import com.memberservice.member.domain.dto.response.ResponseMemberDto;
import com.memberservice.member.domain.entity.Gender;
import com.memberservice.member.domain.entity.Member;
import com.memberservice.member.domain.entity.Role;
import com.memberservice.member.mock.FakeMemberRepository;
import com.memberservice.member.mock.StubIdentifierFactory;
import com.memberservice.member.service.port.IdentifierFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;

class MemberServiceTest {

    private MemberService memberService;
    private final IdentifierFactory stubIdentifierFactory = new StubIdentifierFactory();
    private final RedisTemplate<String, Object> redisTemplates;

    MemberServiceTest(RedisTemplate<String, Object> redisTemplates) {
        this.redisTemplates = redisTemplates;
    }

    @BeforeEach
    void init() {
        this.memberService = new MemberService(
                new FakeMemberRepository(),
                MockAppConfig.passwordEncoder(),
                MockAppConfig.objectMapper(),
                stubIdentifierFactory,
                redisTemplates
        );
    }

    @Test
    @DisplayName("회원가입")
    void signUp() {
        // given
        SignUpMemberDto dto = new SignUpMemberDto(
                "test@test.com",
                "asdf",
                "김영감",
                Gender.MALE,
                "010-0000-0000",
                Role.USER);

        // when
        Member member = memberService.signUp(dto);

        // then
        Assertions.assertNotNull(member);
        Assertions.assertEquals(member.getId(), 0L);
        Assertions.assertEquals(member.getMemberId(), stubIdentifierFactory.generate());
    }

//    @Test
//    @DisplayName("로그인")
//    public void signIn() {
//
//    }
}