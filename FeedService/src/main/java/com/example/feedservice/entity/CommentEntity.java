package com.example.feedservice.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    @Id
    private String commentId;

    private String parentCommentId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Column(columnDefinition = "LONGTEXT")
    private String contents;
}
