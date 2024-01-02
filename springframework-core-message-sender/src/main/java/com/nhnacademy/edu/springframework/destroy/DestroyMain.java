package com.nhnacademy.edu.springframework.destroy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DestroyMain {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSender emailMessageSender = context.getBean("emailMessageSender", MessageSender.class);
            MessageSender emailMessageSender2 = context.getBean("emailMessageSender", MessageSender.class);
        }
    }
}
