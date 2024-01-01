package com.example.demo.type;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.MemberRole;

@Configuration
public class EnumConfig {

    @Bean
    public EnumMapperFactory createEnumMapperFa5ctory() {
        EnumMapperFactory enumMapperFactory = new EnumMapperFactory();
        enumMapperFactory.put("MemberRole", MemberRole.class);
        return enumMapperFactory;
    }
}
