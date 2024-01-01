package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepositoryImpl;
import com.example.demo.type.EnumMapperFactory;
import com.example.demo.type.EnumMapperValue;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final EnumMapperFactory enumMapperFactory;
    private final MemberRepositoryImpl memberRepositoryImpl;

    @GetMapping("/member/roles")
    public ResponseEntity<List<EnumMapperValue>> getMemberRoleEnums() {
        return ResponseEntity.ok(enumMapperFactory.get("MemberRole"));
    }

    @GetMapping("/member/all")
    public ResponseEntity<List<Member>> getAll() {
        return ResponseEntity.ok(memberRepositoryImpl.findAll());
    }

}
