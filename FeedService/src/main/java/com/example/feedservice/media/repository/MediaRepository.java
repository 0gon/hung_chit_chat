package com.example.feedservice.media.repository;

import com.example.feedservice.media.entity.MediaEntity;
import com.example.feedservice.feed.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<MediaEntity, String>, MediaRepositoryCustom {

    List<MediaEntity> findAllByFeed(FeedEntity feed);

}
