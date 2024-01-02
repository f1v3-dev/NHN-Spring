package com.nhnacademy.edu.springframework.init;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitMain {
    public static void main(String[] args) {

        User user = new User("f1v3@kakao.com", "010-2717-8134");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSender emailMessageSender = context.getBean("emailMessageSender", MessageSender.class);
            MessageSender smsMessageSender1 = context.getBean("smsMessageSender", MessageSender.class);
            MessageSender smsMessageSender2 = context.getBean("smsMessageSender", MessageSender.class);

        }
    }
}
