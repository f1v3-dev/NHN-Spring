package com.nhnacademy.edu.springframework.dooray.service;

import com.nhnacademy.edu.springframework.dooray.User;

public interface MessageSender {

    boolean sendMessage(User user, String message);
}
