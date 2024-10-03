package com.example.feedservice.media.repository.impl;


import com.example.feedservice.media.entity.MediaEntity;
import com.example.feedservice.media.entity.QMediaEntity;
import com.example.feedservice.media.repository.MediaRepositoryCustom;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.example.feedservice.media.entity.QMediaEntity.*;

@RequiredArgsConstructor
public class MediaRepositoryImpl implements MediaRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MediaEntity> findByFeedId(String feedId) {

        return queryFactory.select(mediaEntity)
                .from(mediaEntity)
                .where(mediaEntity.feed.feedId.eq(feedId))
                .fetch();

    }
}
