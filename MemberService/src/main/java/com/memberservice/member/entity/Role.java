package com.memberservice.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    USER("사용자"),
    ADMIN("관리자");

    private final String desc;
}
