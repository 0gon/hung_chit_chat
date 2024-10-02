package com.example.feedservice.feed.entity;

import com.example.feedservice.comment.entity.CommentEntity;
import com.example.feedservice.common.entity.BaseEntity;
import com.example.feedservice.media.entity.MediaEntity;
import com.example.feedservice.reaction.entity.ReactionEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "feed")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedEntity extends BaseEntity {

    @Id
    @Column(name = "feed_id")
    private String feedId;

    @Column(nullable = false)
    private String memberId;

    // 공개 범위 FRIEND, PUBLIC
    private String publicScope;

    @Column(columnDefinition = "LONGTEXT")
    private String contents;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)            // 부모엔티티(feedEntity)가 모든 동작을 할때 자식엔티티(CommentEntity)가 영향 받음 Ex) feedEntity 삭제 -> 연관된 CommentEntity 모두 삭제
    private List<CommentEntity> commentList = new ArrayList<>();        // 양방향

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
    private List<MediaEntity> mediaList = new ArrayList<>();              // 양방향

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "feed_id")   // FK 이므로 테이블 생성 안해도댐
    private List<ReactionEntity> reactionList = new ArrayList<>();      // 단방향 설정

    @Builder
    public FeedEntity(String feedId, String memberId, String publicScope, String contents) {
        this.feedId = feedId;
        this.memberId = memberId;
        this.publicScope = publicScope;
        this.contents = contents;
    }

    public void changeContents(String contents) {
        this.contents = contents;
    }

    /**
     * 편의메서드
     * */
    public void addReaction(ReactionEntity reactionEntity) {
        this.getReactionList().add(reactionEntity);
    }

    public void addMedia(MediaEntity mediaEntity) {
        this.mediaList.add(mediaEntity);
    }

    @Override
    public String getId() {
        return this.feedId;
    }
}
