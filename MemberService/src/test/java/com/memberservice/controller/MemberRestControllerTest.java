package com.memberservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memberservice.member.controller.MemberRestController;
import com.memberservice.member.dto.request.SignUpMemberDto;
import com.memberservice.member.repository.MemberRepository;
import com.memberservice.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MemberRestController.class)
@MockBean(JpaMetamodelMappingContext.class)
class MemberRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockBean
    MemberService service;
    @MockBean
    MemberRepository repository;

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



        mockMvc.perform(post("/members/auth/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("success"));
    }
}