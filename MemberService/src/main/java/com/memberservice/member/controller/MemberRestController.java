package com.memberservice.member.controller;

import com.memberservice.member.dto.request.RequestLoginDto;
import com.memberservice.member.dto.request.SignUpMemberDto;
import com.memberservice.member.dto.response.ResponseMemberDto;
import com.memberservice.member.dto.response.ResponseTokenDto;
import com.memberservice.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * */
    @PostMapping("/auth/signUp")
    public ResponseEntity<String> sighUp(@RequestBody @Valid SignUpMemberDto dto) {

        memberService.save(dto);
        return ResponseEntity.ok("success");
    }

    /**
     * 사용자의 아이디와 비밀번호를 받아 응답으로 엑세스와 리프레시 토큰을 반환
     * todo: 토큰의 저장위치가 정해진다면 수정해야됨
     */
    @PostMapping("/auth/signIn")
    public ResponseEntity<ResponseTokenDto> signIn (@RequestBody RequestLoginDto requestLoginDto) {

        ResponseTokenDto responseTokenDto = memberService.signIn(requestLoginDto);
        return ResponseEntity.ok(responseTokenDto);
    }

    /**
     * 사용자의 아이디와 비밀번호를 받아 응답으로 엑세스와 리프레시 토큰을 반환
     */
    @GetMapping("/auth/users/{memberId}")
    public ResponseEntity<ResponseMemberDto> signIn (@PathVariable String memberId) {

        ResponseMemberDto responseMemberDto = memberService.getMemberByMemberId(memberId);
        return ResponseEntity.ok(responseMemberDto);
    }
}
