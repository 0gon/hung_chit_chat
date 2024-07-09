package com.redis.redisChat.demo.domain.member.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private String id;
    private String nickName;

}
