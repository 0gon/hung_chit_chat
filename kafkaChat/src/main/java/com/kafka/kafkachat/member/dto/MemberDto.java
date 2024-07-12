package com.kafka.kafkachat.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String name;
    private String password;
    private int gender;
    private LocalDateTime createAt;
    private String phoneNubmer;
}
