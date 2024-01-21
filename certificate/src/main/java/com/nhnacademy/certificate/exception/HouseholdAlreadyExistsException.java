package com.nhnacademy.certificate.exception;

public class HouseholdAlreadyExistsException extends RuntimeException {
    public HouseholdAlreadyExistsException() {
        super("Household Already Exists!");
    }
}
