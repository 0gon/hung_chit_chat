package com.redis.redisChat.demo.server.domain.room.repository;

import com.redis.redisChat.demo.server.domain.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT DISTINCT r " +
            "FROM Room r " +
            "JOIN r.MemberRooms mr " +
            "WHERE mr.member.id = :memberId")
    List<Room> findRoomsByMemberId(String memberId);
}