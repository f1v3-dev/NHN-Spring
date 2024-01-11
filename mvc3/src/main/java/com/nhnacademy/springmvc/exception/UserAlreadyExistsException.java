package com.nhnacademy.springmvc.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User Already Exists!!");
    }
}
