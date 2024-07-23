package com.memberservice.converter;

import com.memberservice.dto.request.SignUpMemberDto;
import com.memberservice.entity.Member;
import com.memberservice.entity.Role;

public class Converter {

    /**
     * @Param SignUpMemberDto
     * SignUpmemberDto -> Entity
     */
    public static Member RequestToEntity(SignUpMemberDto signUpMemberDto) {

        return Member.builder()
                .email(signUpMemberDto.getEmail())
                .password(signUpMemberDto.getPassword())
                .name(signUpMemberDto.getName())
                .phoneNumber(signUpMemberDto.getPhoneNumber())
                .role(Role.USER)
                .gender(signUpMemberDto.getGender())
                .build();
    }
}
