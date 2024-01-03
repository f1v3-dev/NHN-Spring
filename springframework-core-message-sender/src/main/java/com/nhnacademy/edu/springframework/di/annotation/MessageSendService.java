package com.nhnacademy.edu.springframework.di.annotation;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageSendService {
    private MessageSender messageSender;

    @Autowired
    public MessageSendService(@SMS MessageSender messageSender) {
        System.out.println("------------ injection by @SMS");
        this.messageSender = messageSender;
    }

    public void setSmsMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage(User user, String message) {
        messageSender.sendMessage(user, message);
    }
}
