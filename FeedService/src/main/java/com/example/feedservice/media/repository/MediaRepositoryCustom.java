package com.example.feedservice.media.repository;


import com.example.feedservice.media.entity.MediaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MediaRepositoryCustom {

    List<MediaEntity> findByFeedId(String feedId);
}
