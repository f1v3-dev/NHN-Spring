package com.nhnacademy.edu.springframework.dooray.config;

import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework.dooray")
@EnableAspectJAutoProxy
public class JavaConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(restTemplate(),
                "https://hook.dooray.com/services/3204376758577275363/3707922642738216364/WdJUIozGSgGdnxhQnS1lSA");
    }
}