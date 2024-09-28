package com.memberservice.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@DisplayName("실제 설정과 테스트 설정이 같은지 확인")
public class ConfigurationConsistencyTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void PasswordEncoder가_일치한지_확인() {
        PasswordEncoder mockEncoder = MockAppConfig.passwordEncoder();
        PasswordEncoder actualEncoder = context.getBean(PasswordEncoder.class);

        // 두 인스턴스가 동일한지 확인
        Assertions.assertEquals(mockEncoder.getClass(), actualEncoder.getClass());
    }

    @Test
    void ObjectMapper가_일치한지_확인() {
        ObjectMapper mockEncoder = MockAppConfig.objectMapper();
        ObjectMapper actualEncoder = context.getBean(ObjectMapper.class);

        // 두 인스턴스가 동일한지 확인
        Assertions.assertEquals(mockEncoder.getClass(), actualEncoder.getClass());

        throw new RuntimeException("");
    }
}
