package com.example.feedservice.reaction.entity;


import com.example.feedservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "reactions")
public class ReactionEntity extends BaseEntity {

    @Id
    @Column(name = "reaction_id")
    private String reactionId;

    @Column(name = "feed_id", nullable = false)
    private String feedId;

    @Column(nullable = false)
    private String memberId;

    @Override
    public String getId() {
        return this.reactionId;
    }
}