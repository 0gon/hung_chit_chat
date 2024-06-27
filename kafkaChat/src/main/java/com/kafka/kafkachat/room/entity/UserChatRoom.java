package com.kafka.kafkachat.room.entity;

import com.kafka.kafkachat.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserChatRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member members;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRooms;



    // ===          연관관계 편의 메서드         === //
    public UserChatRoom(Member member, ChatRoom chatRoom) {
        member.setUserChatRooms(this);
        chatRoom.setUserChatRoom(this);
    }

    public void setMember(Member member) {
        this.members = member;
        member.getUserChatRooms().add(this);
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRooms = chatRoom;
        chatRoom.getRooms().add(this);
    }
}
