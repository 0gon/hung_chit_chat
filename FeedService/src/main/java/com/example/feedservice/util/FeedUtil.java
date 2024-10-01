package com.example.feedservice.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FeedUtil {

    @Bean
    public String getUUID() {
        return UUID.randomUUID().toString();
    }
}
