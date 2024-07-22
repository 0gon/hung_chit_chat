package com.redis.redisChat.demo.domain.member.entity;

import com.redis.redisChat.demo.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    public void test() {

        Member member = new Member();
        member.setId("test");
        member.setNickName("no 1");

        // Using EntityManager
        em.persist(member);
        Member findMember = em.find(Member.class, member.getId());

        System.out.println(member.toString());
        System.out.println(findMember.toString());

        System.out.println(member == findMember);  // true

        System.out.println(System.identityHashCode(member));
        System.out.println(System.identityHashCode(findMember));
    }

    @Test
    @Transactional
    public void testWithRepository() {

        Member member = new Member();
        member.setId("test");
        member.setNickName("no 1");

        Member saveMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(saveMember.getId()).get();

        System.out.println(member.toString());
        System.out.println(saveMember.toString());
        System.out.println(findMember.toString());

        System.out.println(member == saveMember);  // false
        System.out.println(member == findMember);  // false
        System.out.println(saveMember == findMember);  // true

        System.out.println(System.identityHashCode(member));
        System.out.println(System.identityHashCode(saveMember));
        System.out.println(System.identityHashCode(findMember));
    }


}