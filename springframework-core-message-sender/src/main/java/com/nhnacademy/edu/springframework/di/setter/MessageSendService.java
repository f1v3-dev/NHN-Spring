package com.nhnacademy.edu.springframework.di.setter;

public class MessageSendService {
    private MessageSender messageSender;

    // Setter Injection은 기본 생성자가 필수
    public MessageSendService() {
    }

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage(User user, String message) {
        messageSender.sendMessage(user, message);
    }
}
