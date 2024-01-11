package com.nhnacademy.edu.springframework.aop.service;


import com.nhnacademy.edu.springframework.aop.annotation.TraceTimeElapsed;
import org.springframework.stereotype.Component;

@Component
public class SmsMessageSender implements MessageSender {

    public SmsMessageSender() {
        System.out.println("=============> SmsMessageSender initiated!!");
    }

    public void init() {
        System.out.println("init method called in SmsMessageSender");
    }

    @Override
    @TraceTimeElapsed(value = "SMS")
    public void sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + message);
    }
}
