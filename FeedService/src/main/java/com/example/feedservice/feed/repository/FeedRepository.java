package com.example.feedservice.feed.repository;

import com.example.feedservice.feed.entity.FeedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<FeedEntity, String> {
}
