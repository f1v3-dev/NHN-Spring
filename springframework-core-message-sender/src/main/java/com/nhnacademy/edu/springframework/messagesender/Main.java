package com.nhnacademy.edu.springframework.messagesender;

public class Main {
    public static void main(String[] args) {
        User user = new User("f1v3@kakao.com", "010-2717-8134");
        sendSmsMessage(user, "hello, my name is Seungjo");
    }


    public static void sendEmailMessage(User user, String message) {
        System.out.println("Email Message Sent " + user.getEmail() + " : " + message);
    }

    public static void sendSmsMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + message);
    }

}