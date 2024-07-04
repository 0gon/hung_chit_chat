package com.redis.redisChat.demo.domain.room.entity;

import java.util.List;

import com.redis.redisChat.demo.domain.member.entity.Member;
import com.redis.redisChat.demo.domain.memberRoom.entity.MemberRoom;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    
    @Id @GeneratedValue
    private Long id; // 나중에 서버id + 숫자로 변경하기 ex)Snowflake
    
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<MemberRoom> MemberRoom;
    
}
