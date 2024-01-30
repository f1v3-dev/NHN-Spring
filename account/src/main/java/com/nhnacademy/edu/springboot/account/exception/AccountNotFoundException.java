package com.nhnacademy.edu.springboot.account.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException() {
        super("Account Not Found!!");
    }
}
