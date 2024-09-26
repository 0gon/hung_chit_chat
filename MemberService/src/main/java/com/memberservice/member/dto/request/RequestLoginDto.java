package com.memberservice.member.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestLoginDto {

    private String email;

    private String password;
}
