package com.redis.redisChat.demo.server.domain.friendship.repository;

import com.redis.redisChat.demo.server.domain.friendship.entity.Friendship;
import com.redis.redisChat.demo.server.domain.friendship.entity.FriendshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipReopository extends JpaRepository<Friendship, FriendshipId> {

    List<Friendship> findByMember_Id(String memberId);
}