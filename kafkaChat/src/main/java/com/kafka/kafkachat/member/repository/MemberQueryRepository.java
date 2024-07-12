package com.kafka.kafkachat.member.repository;

import com.kafka.kafkachat.chat.entity.ChatRoom;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final EntityManager em;

    public List<ChatRoom> findRoomByMemberId(Long id){
        return em.createQuery("select distinct cr from Member m" +
                " join m.userChatRooms ucr" +
                " join ucr.chatRoom cr" +
                " where ucr.member.id = :id", ChatRoom.class)
                .setParameter("id", id)
                .getResultList();
    }
}
