package com.nhnacademy.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User Already Exists!!");
    }
}
