package com.nhnacademy.certificate.exception;

public class HouseholdNotFoundException extends RuntimeException {
    public HouseholdNotFoundException() {
        super("Household Not Found!");
    }
}
