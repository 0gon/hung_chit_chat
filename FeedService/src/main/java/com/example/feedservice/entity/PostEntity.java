package com.example.feedservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
public class PostEntity extends BaseEntity {

    @Id
    @Column(name = "post_id")
    private String postId;

    @Column(nullable = false)
    private String memberId;

    // 공개 범위 FRIEND, PUBLIC
    private String publicScope;

    @Column(columnDefinition = "LONGTEXT")
    private String contents;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommentEntity> commentList = new ArrayList<>();        // 양방향

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<FileEntity> fileList = new ArrayList<>();              // 양방향

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<LikeEntity> likeList = new ArrayList<>();      // 단방향 설정

}
