package com.memberservice;

import com.memberservice.member.domain.dto.request.SignUpMemberDto;
import com.memberservice.member.domain.entity.Gender;
import com.memberservice.member.domain.entity.Member;
import com.memberservice.member.domain.entity.Role;
import com.memberservice.member.service.port.MemberRepository;
import com.memberservice.member.service.MemberService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class MemberServiceApplicationTests {

    @Test
    void contextLoads() {

    }
}
