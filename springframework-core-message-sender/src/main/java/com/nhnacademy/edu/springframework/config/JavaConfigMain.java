package com.nhnacademy.edu.springframework.config;


import com.nhnacademy.edu.springframework.config.service.MessageSendService;
import com.nhnacademy.edu.springframework.config.service.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigMain {
    public static void main(String[] args) {
        User user = new User("f1v3@kakao.com", "010-2717-8134");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.config");

        MessageSendService service = context.getBean("messageSendService", MessageSendService.class);
        service.doSendMessage(user, "hello");
    }
}
