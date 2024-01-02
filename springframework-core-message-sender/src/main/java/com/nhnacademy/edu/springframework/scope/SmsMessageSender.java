package com.nhnacademy.edu.springframework.scope;


public class SmsMessageSender implements MessageSender {

    public SmsMessageSender() {
        System.out.println("=============> SmsMessageSender initiated!!");
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + message);
    }
}
