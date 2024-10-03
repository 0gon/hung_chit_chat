package com.memberservice.member.domain.dto.request;

import com.memberservice.member.domain.entity.Gender;
import com.memberservice.member.domain.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class SignUpMemberDto {

    @Email
    @NotBlank(message = "required email")
    private String email;

    @NotBlank(message = "required password")
    private String password;

    @NotBlank(message = "required name")
    private String name;
    private Gender gender;
    private String phoneNumber;
    private Role role;

    /**
     * 비밀번호 encode
     */
    public void EncodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }



}
