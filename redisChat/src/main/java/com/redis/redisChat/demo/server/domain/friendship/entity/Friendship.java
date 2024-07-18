package com.redis.redisChat.demo.server.domain.friendship.entity;

import com.redis.redisChat.demo.server.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "friend_id", insertable = false, updatable = false)
    private Member friend;

}
