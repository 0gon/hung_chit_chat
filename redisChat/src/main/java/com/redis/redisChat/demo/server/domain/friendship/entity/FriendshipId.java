package com.redis.redisChat.demo.server.domain.friendship.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendshipId implements Serializable {
    private String memberId;
    private String friendId;
}