package com.nhnacademy.edu.springframework.service;

public class Main {
    public static void main(String[] args) {

        User user = new User("f1v3@kakao.com", "010-2717-8134");

        new MessageSendService(new SmsMessageSender())
                .doSendMessage(user, "message!!!");
    }
}