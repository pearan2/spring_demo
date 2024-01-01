package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.filter.LogFilter;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}

	@Bean
	public FilterRegistrationBean<LogFilter> filterBean() {

		var registrationBean = new FilterRegistrationBean<LogFilter>(new LogFilter());
		registrationBean.setOrder(Integer.MIN_VALUE); // 필터 여러개 적용 시 순번
		registrationBean.addUrlPatterns("/*"); // 전체 URL 포함
		return registrationBean;
	}
}
