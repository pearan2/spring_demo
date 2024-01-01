package com.example.demo.repository;

import java.util.List;
import com.example.demo.entity.Member;

public interface MemberRepository {
    List<Member> findAll();
}
