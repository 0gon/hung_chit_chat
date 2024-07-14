package com.redis.redisChat.demo.domain.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.redis.redisChat.demo.domain.room.entity.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT DISTINCT r " +
            "FROM Room r " +
            "JOIN r.MemberRooms mr " +
            "WHERE mr.member.id = :memberId")
    List<Room> findRoomsByMemberId(String memberId);
}