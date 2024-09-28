package com.memberservice.member.controller;

import com.memberservice.member.domain.dto.request.RequestLoginDto;
import com.memberservice.member.domain.dto.request.SignUpMemberDto;
import com.memberservice.member.domain.dto.response.ResponseMemberDto;
import com.memberservice.member.domain.dto.response.ResponseTokenDto;
import com.memberservice.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/test")
    public String test() {
        return "test Online1234";
    }

    /**
     * 회원가입
     */
    @PostMapping("/auth/signUp")
    public ResponseEntity<Map<String, String>> sighUp(@RequestBody @Valid SignUpMemberDto dto) {

        memberService.signUp(dto);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        return ResponseEntity.ok(result);
    }


    /**
     * 로그인
     */
    @PostMapping("/auth/signIn")
    public ResponseEntity<ResponseTokenDto> signIn (@RequestBody RequestLoginDto requestLoginDto) {

        ResponseTokenDto responseTokenDto = memberService.signIn(requestLoginDto);
        return ResponseEntity.ok(responseTokenDto);
    }

    /**
     * 뭐하는 애일까?
     */
    @GetMapping("/auth/users/{memberId}")
    public ResponseEntity<ResponseMemberDto> signIn (@PathVariable String memberId) {

        ResponseMemberDto responseMemberDto = memberService.getMemberByMemberId(memberId);
        return ResponseEntity.ok(responseMemberDto);
    }
}
