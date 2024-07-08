package com.redis.redisChat.demo.domain.friendship.entity;

import com.redis.redisChat.demo.domain.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@IdClass(FriendshipId.class)
public class Friendship {

    @Id
    @Column(name = "member_id")
    private String memberId;

    @Id
    @Column(name = "friend_id")
    private String friendId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "friend_id")
    private Member friend;

}
