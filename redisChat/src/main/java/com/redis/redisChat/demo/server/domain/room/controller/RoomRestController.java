package com.redis.redisChat.demo.server.domain.room.controller;

import com.redis.redisChat.demo.comm.CookieHandler;
import com.redis.redisChat.demo.server.domain.room.RoomDto;
import com.redis.redisChat.demo.server.domain.room.RoomService;
import com.redis.redisChat.demo.server.domain.room.entity.Room;
import com.redis.redisChat.demo.server.domain.room.repository.RoomRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class RoomRestController {

    private final RoomService service;
    private final RoomRepository roomRepository;

    @PostMapping("/api/room/makeRoom")
    public ResponseEntity<Void> makeRoom(@RequestBody List<String> memberIds, HttpServletRequest request) {
        String memberId = CookieHandler.getCookieValue(request, "member_id"); // 요청자 id
        memberIds.add(memberId); // 방에 본인도 들어가야 하니까 일단 여기서 넣음
        service.makeRoom(memberIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/room/getRooms")
    public ResponseEntity getRooms(HttpServletRequest request) {
        String memberId = CookieHandler.getCookieValue(request, "member_id"); // 요청자 id
        List<Room> rooms = roomRepository.findRoomsByMemberId(memberId);
        List<RoomDto> roomDtos = rooms.stream()
                .map(RoomDto::of)
                .toList();
        return ResponseEntity.ok(roomDtos);
    }

}
