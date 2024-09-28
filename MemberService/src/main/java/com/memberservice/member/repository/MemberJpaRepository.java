package com.memberservice.member.repository;

import com.memberservice.member.domain.entity.Member;
import com.memberservice.member.service.port.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long>, MemberRepository {

}
