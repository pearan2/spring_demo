package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Member;
import com.example.demo.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MemberRepositoryImpl implements MemberRepository {

    private final JPAQueryFactory qf;

    @Override
    public List<Member> findAll() {
        var qMember = new QMember("m1");
        return qf.select(qMember).from(qMember).fetch();
    }
}
