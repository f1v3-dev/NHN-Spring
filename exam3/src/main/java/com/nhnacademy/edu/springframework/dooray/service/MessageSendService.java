package com.nhnacademy.edu.springframework.dooray.service;

import com.nhnacademy.edu.springframework.dooray.User;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {

    private final MessageSender messageSender;

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage(User user, String message) {
        messageSender.sendMessage(user, message);
    }
}
