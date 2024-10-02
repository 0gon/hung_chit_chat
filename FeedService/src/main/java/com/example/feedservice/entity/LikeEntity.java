package com.example.feedservice.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "likes")
public class LikeEntity extends BaseEntity {

    @Id
    @Column(name = "like_id")
    private String likeId;

    @Column(name = "post_id", nullable = false)
    private String postId;

    @Column(nullable = false)
    private String memberId;

    @Override
    public String getId() {
        return this.likeId;
    }
}