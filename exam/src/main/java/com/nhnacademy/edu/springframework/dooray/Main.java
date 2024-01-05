package com.nhnacademy.edu.springframework.dooray;

import com.nhnacademy.edu.springframework.dooray.service.MessageSendService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        User user = new User("정승조");

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSendService service = context.getBean("messageSendService", MessageSendService.class);
            service.doSendMessage(user, "안녕하세요");
        }
    }
}
