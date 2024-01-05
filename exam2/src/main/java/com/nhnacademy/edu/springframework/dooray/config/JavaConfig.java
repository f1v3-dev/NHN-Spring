package com.nhnacademy.edu.springframework.dooray.config;

import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.dooray.aop.ElapsedTimeAspect;
import com.nhnacademy.edu.springframework.dooray.service.DoorayMessageSender;
import com.nhnacademy.edu.springframework.dooray.service.MessageSendService;
import com.nhnacademy.edu.springframework.dooray.service.MessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@Configuration
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

    @Bean
    public MessageSender messageSender() {
        return new DoorayMessageSender(doorayHookSender());
    }

    @Bean
    public MessageSendService messageSendService() {
        return new MessageSendService(messageSender());
    }

    @Bean
    public ElapsedTimeAspect elapsedTimeAspect() {
        return new ElapsedTimeAspect();
    }
}
