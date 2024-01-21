package com.nhnacademy.certificate.exception;

public class HouseholdMovementAddressAlreadyExistsException extends RuntimeException {
    public HouseholdMovementAddressAlreadyExistsException() {
        super("Household Movement Address Already Exists");
    }
}
