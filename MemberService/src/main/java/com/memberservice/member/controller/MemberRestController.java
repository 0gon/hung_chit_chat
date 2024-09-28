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
     * 로그인 시 멤버 ID로 사용자 체크 후 Role 담아서 반환 -> 추후 어드민 페이지시 필요
     * 로그인에 넣으면 그 이후 단계 불필요한 조회 발생 X
     * Gateway Service -> filter -> JwtRequestFilter 참고
     * @param memberId - String: Member Id
     */
    @GetMapping("/auth/users/{memberId}")
    public ResponseEntity<ResponseMemberDto> getMemberAndRoleByMemberId (@PathVariable String memberId) {

        ResponseMemberDto responseMemberDto = memberService.getMemberByMemberId(memberId);
        return ResponseEntity.ok(responseMemberDto);
    }
}
