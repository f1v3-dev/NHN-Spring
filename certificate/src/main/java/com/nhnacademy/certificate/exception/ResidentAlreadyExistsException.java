package com.nhnacademy.certificate.exception;

public class ResidentAlreadyExistsException extends RuntimeException {
    public ResidentAlreadyExistsException() {
        super("Resident Serial Number already exists!");
    }
}
