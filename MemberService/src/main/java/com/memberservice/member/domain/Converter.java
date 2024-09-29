package com.memberservice.member.domain;

import com.memberservice.member.domain.dto.jackson.MemberView;
import com.memberservice.member.domain.dto.request.SignUpMemberDto;
import com.memberservice.member.domain.dto.response.ResponseMemberDto;
import com.memberservice.member.domain.entity.Member;
import com.memberservice.member.domain.entity.Role;

public class Converter {

    /**
     * SignUpmemberDto -> Entity
     */
    public static Member RequestToEntity(SignUpMemberDto signUpMemberDto, String identifier) {

        return Member.builder()
                .memberId(identifier)
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

    public static MemberView ResponseMemberDtoToView(ResponseMemberDto responseMemberDto) {

        return MemberView.builder()
                .memberId(responseMemberDto.getMemberId())
                .email(responseMemberDto.getEmail())
                .password(responseMemberDto.getPassword())
                .role(responseMemberDto.getRole())
                .build();
    }
}
