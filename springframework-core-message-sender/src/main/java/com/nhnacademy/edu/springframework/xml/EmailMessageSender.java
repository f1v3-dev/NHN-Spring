package com.nhnacademy.edu.springframework.xml;

public class EmailMessageSender implements MessageSender {

    @Override
    public void sendMessage(com.nhnacademy.edu.springframework.xml.User user, String message) {
        System.out.println("Email Message Sent to " + user.getEmail() + " : " + message);

    }
}
