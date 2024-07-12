package com.kafka.kafkachat.member.entity;

import com.kafka.kafkachat.chat.entity.UserChatRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private int gender;
    
    private LocalDateTime createAt;

    private String phoneNumber;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    public Member(String username) {
        this.username = username;
    }

    @Builder
    public Member(Long id, String username, String password, int gender, LocalDateTime createAt, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.createAt = createAt;
        this.phoneNumber = phoneNumber;
    }


    // ===          연관관계 편의 메서드         === //
    public void addUserChatRoom(UserChatRoom userChatRooms) {
        this.userChatRooms.add(userChatRooms);
        userChatRooms.setMember(this);
    }
}
