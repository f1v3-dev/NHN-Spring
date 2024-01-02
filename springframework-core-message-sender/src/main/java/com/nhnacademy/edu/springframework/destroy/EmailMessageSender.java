package com.nhnacademy.edu.springframework.destroy;

public class EmailMessageSender implements MessageSender {

    public EmailMessageSender() {
        System.out.println(" ------------> EmailMessageSender initiated!!");
    }

    public void cleanup() {
        System.out.println("EmailMessageSender cleanup called!!");
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("Email Message Sent to " + user.getEmail() + " : " + message);

    }
}
