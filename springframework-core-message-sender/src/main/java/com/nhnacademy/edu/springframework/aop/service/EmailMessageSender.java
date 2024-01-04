package com.nhnacademy.edu.springframework.aop.service;

import com.nhnacademy.edu.springframework.aop.annotation.TraceTimeElapsed;
import org.springframework.stereotype.Component;

@Component
public class EmailMessageSender implements MessageSender {

    public EmailMessageSender() {
        System.out.println(" ------------> EmailMessageSender initiated!!");
    }

    @Override
    @TraceTimeElapsed(value = "Email")
    public void sendMessage(User user, String message) {
        System.out.println("Email Message Sent to " + user.getEmail() + " : " + message);

    }
}
