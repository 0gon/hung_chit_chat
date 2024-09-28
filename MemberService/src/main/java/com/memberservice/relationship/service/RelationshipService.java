//package com.memberservice.relationship.service;
//
//import com.memberservice.member.entity.Member;
//import com.memberservice.relationship.entity.Relation;
//import com.memberservice.relationship.entity.Relationship;
//import com.memberservice.relationship.entity.RelationshipPK;
//import com.memberservice.relationship.repository.RelationshipRepository;
//import com.memberservice.member.service.port.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class RelationshipService {
//
//    RelationshipRepository repository;
//    MemberRepository memberRepository;
//
//    public void save(String actorId, String receiverId, Relation relation) {
//        Member actor = memberRepository.findByMemberId(actorId).get();
//        Member receiver = memberRepository.findByMemberId(receiverId).get();
//        RelationshipPK pk = new RelationshipPK(actor, receiver);
//        Relationship r = Relationship.builder().build();
//        repository.save(r);
//    }
//}
