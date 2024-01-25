package com.nhnacademy.springboot.gateway.exception;

public class ValidationFailedException extends RuntimeException{

    public ValidationFailedException() {
        super("Validation Failed!!!");
    }
}
