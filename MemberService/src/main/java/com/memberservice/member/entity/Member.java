package com.memberservice.member.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.memberservice.member.dto.jackson.Views;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
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

    @PrePersist
    public void prePersist() {
        this.memberId = UUID.randomUUID().toString();
    }

}
