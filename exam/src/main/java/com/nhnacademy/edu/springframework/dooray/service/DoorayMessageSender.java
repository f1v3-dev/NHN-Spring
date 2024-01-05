package com.nhnacademy.edu.springframework.dooray.service;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.dooray.User;
import com.nhnacademy.edu.springframework.dooray.annotation.TraceElapsedTime;


public class DoorayMessageSender implements MessageSender {
    private final DoorayHookSender hookSender;

    public DoorayMessageSender(DoorayHookSender hookSender) {
        this.hookSender = hookSender;
    }

    @Override
    @TraceElapsedTime
    public boolean sendMessage(User user, String message) {
        hookSender.send(DoorayHook.builder()
                .botName(user.getName())
                .text(message)
                .build());

        return true;
    }
}
