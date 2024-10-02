package com.example.feedservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)            // 부모엔티티(PostEntity)가 모든 동작을 할때 자식엔티티(CommentEntity)가 영향 받음 Ex) PostEntity 삭제 -> 연관된 CommentEntity 모두 삭제
    private List<CommentEntity> commentList = new ArrayList<>();        // 양방향  

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<FileEntity> fileList = new ArrayList<>();              // 양방향

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")   // FK 이므로 테이블 생성 안해도댐
    private List<LikeEntity> likeList = new ArrayList<>();      // 단방향 설정

    @Builder
    public PostEntity(String postId, String memberId, String publicScope, String contents) {
        this.postId = postId;
        this.memberId = memberId;
        this.publicScope = publicScope;
        this.contents = contents;
    }

    /**
     * 편의메서드
     * */
    public void addLike(LikeEntity likeEntity) {
        this.getLikeList().add(likeEntity);
    }

    public void addFile(FileEntity fileEntity) {
        this.fileList.add(fileEntity);
    }

    @Override
    public String getId() {
        return this.postId;
    }
}
