package com.memberservice.dto.request;

import com.memberservice.entity.Gender;
import com.memberservice.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpMemberDto {

    private String email;
    private String password;
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
