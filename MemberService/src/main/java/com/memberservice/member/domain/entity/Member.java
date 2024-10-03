package com.memberservice.member.domain.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.memberservice.member.domain.dto.jackson.Views;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonView(Views.MemberIdAndEmail.class)
    private String memberId;

    @Column(unique = true)
    @JsonView(Views.MemberIdAndEmail.class)
    private String email;

    private String password;

    private String name;

    private Gender gender;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

}
