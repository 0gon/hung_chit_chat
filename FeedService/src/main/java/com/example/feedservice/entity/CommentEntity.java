package com.example.feedservice.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    private String commentId;

    private String parentCommentId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Column(columnDefinition = "LONGTEXT")
    private String contents;

    /**
     * TODO :: 편의메서드 적용 해야함
     * */
    public void setPost(PostEntity post) {
        this.post = post;
        post.getCommentList().add(this);
    }

    @Override
    public String getId() {
        return this.commentId;
    }
}
