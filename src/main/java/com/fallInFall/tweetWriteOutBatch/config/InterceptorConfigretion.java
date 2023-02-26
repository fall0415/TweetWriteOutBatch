package com.fallInFall.tweetWriteOutBatch.config;

import com.fallInFall.tweetWriteOutBatch.interceptor.RestTemplateBaseInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class InterceptorConfigretion {
    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder().additionalInterceptors(new RestTemplateBaseInterceptor());
    }
}
