package com.nhnacademy.springmvc.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super("Student Not Found!!");
    }
}
