package com.kafka.kafkachat;

import com.kafka.kafkachat.member.entity.Member;
import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.chat.entity.UserChatRoom;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1(){
            Member member = new Member("User1");
            Member member2 = new Member("User2");
            Member member3 = new Member("User3");

            ChatRoom chatRoom = new ChatRoom("Welcome");
            ChatRoom chatRoom2 = new ChatRoom("Welcome2");

            UserChatRoom userChatRoom = new UserChatRoom(member, chatRoom);
            UserChatRoom userChatRoom2 = new UserChatRoom(member2, chatRoom);
            UserChatRoom userChatRoomE = new UserChatRoom(member, chatRoom2);
            UserChatRoom userChatRoomE2 = new UserChatRoom(member3, chatRoom2);

            // 연관관계 편의 메서드로 연관관계 설정
            member.addUserChatRoom(userChatRoom);
            member2.addUserChatRoom(userChatRoom2);

            member.addUserChatRoom(userChatRoomE);
            member3.addUserChatRoom(userChatRoomE2);

            chatRoom.addUserChatRoom(userChatRoom);
            chatRoom.addUserChatRoom(userChatRoom2);

            chatRoom2.addUserChatRoom(userChatRoomE);
            chatRoom2.addUserChatRoom(userChatRoomE2);

            em.persist(member);
            em.persist(member2);
            em.persist(chatRoom);

            em.persist(member3);
            em.persist(chatRoom2);
        }
    }
}
