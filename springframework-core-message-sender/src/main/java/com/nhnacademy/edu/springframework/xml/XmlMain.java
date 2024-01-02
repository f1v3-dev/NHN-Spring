package com.nhnacademy.edu.springframework.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMain {
    public static void main(String[] args) {

        User user = new User("f1v3@kakao.com", "010-2717-8134");

        // Spring Bean으로 등록 - 외부의 파일을 불러오기 때문에 try-with-resource
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {

            MessageSender smsMessage = context.getBean("smsMessageSender", MessageSender.class);
            MessageSender emailMessage = context.getBean("emailMessageSender", MessageSender.class);

            smsMessage.sendMessage(user, "sms test");
            emailMessage.sendMessage(user, "email test");
        }
    }
}
