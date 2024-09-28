//package com.memberservice.relationship.controller;
//
//import com.memberservice.relationship.entity.Relation;
//import com.memberservice.relationship.service.RelationshipService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("members/relationship")
//@RequiredArgsConstructor
//public class RelationshipRestController {
//
//    private final RelationshipService service;
//
//    /**
//     * 관계 추가하기
//     */
//    @PostMapping("/insert")
//    public ResponseEntity insert(
//            String actor
//            , String receiver
//            , @RequestParam(defaultValue = Relation.Names.FRIEND) Relation relation) {
//
//
//        service.save(actor, receiver, relation);
//
//        return null;
//    }
//
//    // 관계 변경하기
//
//    // 관계 정보 요청
//
//    //
//}
