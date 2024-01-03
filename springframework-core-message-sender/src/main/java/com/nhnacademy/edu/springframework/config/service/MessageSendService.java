package com.nhnacademy.edu.springframework.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {
    private MessageSender messageSender;

    @Value("${from}")
    private String from;

    @Autowired
    public MessageSendService(@Qualifier("smsMessageSender") MessageSender messageSender) {
        System.out.println("------------ injection");
        this.messageSender = messageSender;
    }

    public void doSendMessage(User user, String message) {
        System.out.println("From : " + from);
        messageSender.sendMessage(user, message);
    }
}
