package com.nhnacademy.certificate.exception;

public class ResidentNotFoundException extends RuntimeException {
    public ResidentNotFoundException() {
        super("해당 주민은 존재하지 않습니다.");
    }
}
