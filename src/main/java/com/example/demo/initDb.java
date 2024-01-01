package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberRole;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class initDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initMember();
    }

    @Service
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void initMember() {
            var devMember = Member.builder().role(MemberRole.ROLE_DEV).nickname("SW").build();
            var userMember = Member.builder().role(MemberRole.ROLE_USER).nickname("Honlee").build();

            em.persist(devMember);
            em.persist(userMember);
            em.flush();
            em.clear();
        }

        public void dbInit2() {
        }

    }
}
