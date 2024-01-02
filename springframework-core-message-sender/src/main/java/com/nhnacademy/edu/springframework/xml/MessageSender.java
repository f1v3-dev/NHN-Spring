package com.nhnacademy.edu.springframework.xml;

import com.nhnacademy.edu.springframework.xml.User;

public interface MessageSender {
    void sendMessage(User user, String message);
}
