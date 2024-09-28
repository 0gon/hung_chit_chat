package com.memberservice.member.repository;

import com.memberservice.member.domain.entity.Member;
import com.memberservice.member.service.port.MemberRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface MemberRepositoryImpl extends MemberRepository, Repository<Member, Long> {

}
