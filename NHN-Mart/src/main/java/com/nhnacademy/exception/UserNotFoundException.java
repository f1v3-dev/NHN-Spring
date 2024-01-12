package com.nhnacademy.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("USER NOT FOUND!!");
    }
}
