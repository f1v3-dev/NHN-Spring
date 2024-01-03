package com.nhnacademy.edu.springframework.di.custom;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomMain {
    public static void main(String[] args) {

        User user = new User("f1v3@kakao.com", "010-2717-8134");

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSendService service = context.getBean("messageSendService", MessageSendService.class);
            service.doSendMessage(user, "hello");
        }
    }
}
