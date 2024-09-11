package com.memberservice.controller;

import com.memberservice.dto.request.SignUpMemberDto;
import com.memberservice.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/test")
    public String test() {
        return "test Online";
    }

    @GetMapping("/test/{memberId}")
    public ResponseEntity<?> retrieveMember(@PathVariable String memberId) {

        memberService.retrieveMemberByMemberId(memberId);
        return ResponseEntity.ok().body(memberService.retrieveMemberByMemberId(memberId));
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> sighUp(@RequestBody @Valid SignUpMemberDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ResponseEntity.badRequest().body("Validation failed: " + errors.toString());
        }

        try {
            memberService.save(dto);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
