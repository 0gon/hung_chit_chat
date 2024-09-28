package com.memberservice.member.converter;

import com.memberservice.member.model.dto.jackson.MemberView;
import com.memberservice.member.model.dto.request.SignUpMemberDto;
import com.memberservice.member.model.entity.Member;
import com.memberservice.member.model.entity.Role;

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

    public static MemberView MemberToView(Member member) {

        return MemberView.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .gender(member.getGender())
                .phoneNumber(member.getPhoneNumber())
                .role(member.getRole())
                .build();
    }
}
