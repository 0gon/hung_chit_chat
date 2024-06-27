package com.kafka.kafkachat;

import com.kafka.kafkachat.member.entity.Member;
import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.chat.entity.UserChatRoom;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

            ChatRoom chatRoom = new ChatRoom("Welcome");

            UserChatRoom userChatRoom = new UserChatRoom(member, chatRoom);
            UserChatRoom userChatRoom2 = new UserChatRoom(member2, chatRoom);

            // 연관관계 편의 메서드로 연관관계 설정
            member.addUserChatRoom(userChatRoom);
            member2.addUserChatRoom(userChatRoom2);
            chatRoom.addUserChatRoom(userChatRoom);
            chatRoom.addUserChatRoom(userChatRoom2);

            em.persist(member);
            em.persist(member2);
            em.persist(chatRoom);
        }
    }
}
