package com.redis.redisChat.demo.domain.friendship.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendshipId implements Serializable {
    private String memberId;
    private String friendId;
}