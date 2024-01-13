package com.nhnacademy.exception;

public class InquiryNotFoundException extends RuntimeException {

    public InquiryNotFoundException() {
        super("INQUIRY NOT FOUND!!");
    }
}
