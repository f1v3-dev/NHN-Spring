package com.nhnacademy.edu.springframework.dooray.service;

import com.nhnacademy.edu.springframework.dooray.User;

public class MessageSendService {

    private MessageSender messageSender;

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage(User user, String message) {
        messageSender.sendMessage(user, message);
    }
}
