package com.kafka.kafkachat.member.controller;

import com.kafka.kafkachat.member.dto.MemberDto;
import com.kafka.kafkachat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member/")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public @ResponseBody ResponseEntity<MemberDto> signup(@RequestBody MemberDto memberDto) {
        try {
            return ResponseEntity.ok().body(memberService.createUser(memberDto));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/users")
    public @ResponseBody ResponseEntity<MemberDto> getUsers(@RequestBody MemberDto memberDto){
        try{
            return ResponseEntity.ok().body(memberService.findUser(memberDto));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
