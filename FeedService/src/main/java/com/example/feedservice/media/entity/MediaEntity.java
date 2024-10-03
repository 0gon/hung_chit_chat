package com.example.feedservice.media.entity;


import com.example.feedservice.common.entity.BaseEntity;
import com.example.feedservice.feed.entity.FeedEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "media")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MediaEntity extends BaseEntity {

    @Id
    @Column(name = "media_id")
    private String mediaId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "feed_id")
    private FeedEntity feed;

    private String mediaName;

    @Lob
    private Long mediaSize;

    private String mediaPath;

    @Builder
    public MediaEntity(String mediaId, String mediaName, Long mediaSize, String mediaPath) {
        this.mediaId = mediaId;
        this.mediaName = mediaName;
        this.mediaSize = mediaSize;
        this.mediaPath = mediaPath;
    }

    /**
     * TODO :: 편의메서드 적용 해야함
     * */

    public void setFeed(FeedEntity feed) {
        this.feed = feed;
        feed.addMedia(this);
    }


    @Override
    public String getId() {
        return this.mediaId;
    }
}
