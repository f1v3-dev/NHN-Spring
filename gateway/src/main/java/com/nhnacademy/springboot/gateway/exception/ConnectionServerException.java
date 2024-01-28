package com.nhnacademy.springboot.gateway.exception;

public class ConnectionServerException extends RuntimeException {

    public ConnectionServerException(String message) {
        super(message);
    }
}
