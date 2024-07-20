package com.redis.redisChat.demo.webSocket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 사용자 아이디와 연결된 웹소켓서버의 내, 외부 ip를 저장하는 엔티티
 * 필요하다면 클라이언트의 기기 정보도 저장하면 될 거 같음
 */
@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SessionInfo {

    @Id
    String memberId;

    String internalIp; // 내부 ip
    String externalIp; // 외부 ip



}
