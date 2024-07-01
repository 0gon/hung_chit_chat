package com.kafka.kafkachat.config.kafka;

import io.lettuce.core.dynamic.annotation.Value;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * kafka 토픽 생성 로직
 * */

@Configuration
public class KafkaTopicConfig {
    
    // TODO :: topicName -> application-A.yml 으로 분리 후 @Value 로 바꾸기

    @Bean
    public NewTopic createTopic() {
        String topicName = "chat-room-A";
        return TopicBuilder.name(topicName)
                .partitions(3) // 파티션 수를 3로 설정
                .replicas(1)   // 복제본 수를 1로 설정
                .build();
    }
}
