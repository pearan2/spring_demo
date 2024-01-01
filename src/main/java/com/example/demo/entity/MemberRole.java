package com.example.demo.entity;

import com.example.demo.type.EnumMapperType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberRole implements EnumMapperType {

    ROLE_ADMIN("관리자"),
    ROLE_USER("일반유저"),
    ROLE_DEV("개발자");

    @Override
    public String getCode() {
        return name();
    }

    @Getter
    private final String title;
}