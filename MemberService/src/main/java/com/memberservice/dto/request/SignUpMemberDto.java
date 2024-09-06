package com.memberservice.dto.request;

import com.memberservice.entity.Gender;
import com.memberservice.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpMemberDto {

    @NotBlank(message = "required email")
    @Email
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
