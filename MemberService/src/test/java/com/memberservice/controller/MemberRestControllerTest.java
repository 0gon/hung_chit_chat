package com.memberservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memberservice.dto.request.SignUpMemberDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MemberRestController.class)
class MemberRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    MemberRestControllerTest() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void sighUp() throws Exception {
        SignUpMemberDto dto = SignUpMemberDto.builder()
                .email("test@example.com")
                .password("asdf")
                .name("name")
                .build();

        String jsonDto = objectMapper.writeValueAsString(dto);



        mockMvc.perform(post("/api/v1/memberService/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("success"));
    }
}