package com.memberservice.member.dto.jackson;

import com.fasterxml.jackson.annotation.JsonView;
import com.memberservice.member.entity.Gender;
import com.memberservice.member.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberView {

    private Long id;

    @JsonView(Views.MemberIdAndEmail.class)
    private String memberId;

    @JsonView(Views.MemberIdAndEmail.class)
    private String email;

    private String password;

    private String name;

    private Gender gender;

    private String phoneNumber;

    private Role role;
}
