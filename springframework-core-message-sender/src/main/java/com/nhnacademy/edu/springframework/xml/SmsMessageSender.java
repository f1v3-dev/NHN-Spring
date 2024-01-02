package com.nhnacademy.edu.springframework.xml;


public class SmsMessageSender implements MessageSender {

    @Override
    public void sendMessage(com.nhnacademy.edu.springframework.xml.User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + message);
    }
}
