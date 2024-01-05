package com.nhnacademy.edu.springframework.dooray;

import com.nhnacademy.edu.springframework.dooray.config.JavaConfig;
import com.nhnacademy.edu.springframework.dooray.service.MessageSendService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        User user = new User("정승조");

        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        MessageSendService service = context.getBean("messageSendService", MessageSendService.class);

        service.doSendMessage(user, "안녕하세요");
    }
}
