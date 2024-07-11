package com.redis.redisChat.demo.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.redisChat.demo.domain.room.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
