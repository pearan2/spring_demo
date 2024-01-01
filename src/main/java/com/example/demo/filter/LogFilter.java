package com.example.demo.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        try {
            chain.doFilter(req, res);

            byte[] requestBody = req.getContentAsByteArray();
            byte[] responseBody = res.getContentAsByteArray();

            String requestURI = req.getRequestURI();
            final String traceId = UUID.randomUUID().toString();

            log.info("request = {}",
                    new RequestLog(traceId, requestURI, req.getMethod(),
                            req.getHeader("Authorization"),
                            new String(requestBody, StandardCharsets.UTF_8),
                            convert(req.getParameterMap())));

            log.info("response = {}",
                    new ResponseLog(traceId, res.getStatus(), new String(responseBody, StandardCharsets.UTF_8)));
        } finally {
            res.copyBodyToResponse();
        }
    }

    private String convert(Map<String, String[]> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    @Data
    @AllArgsConstructor
    private static class RequestLog {
        private String traceId;
        private String uri;
        private String method;
        private String token;
        private String body;
        private String params;
    }

    @Data
    @AllArgsConstructor
    private static class ResponseLog {
        private String traceId;
        private int statusCode;
        private String body;
    }
}
