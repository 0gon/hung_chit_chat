package com.redis.redisChat.demo.domain.friendship.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.redisChat.demo.domain.friendship.entity.Friendship;
import com.redis.redisChat.demo.domain.friendship.entity.FriendshipId;

@Repository
public interface FriendshipReopository extends JpaRepository<Friendship, FriendshipId> {

    List<Friendship> findByMember_Id(String memberId);
}