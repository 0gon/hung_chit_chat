package com.example.feedservice.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "likes")
public class LikeEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private String likeId;

    @Column(name = "post_id", nullable = false)
    private String postId;

    @Column(nullable = false)
    private String memberId;
}
