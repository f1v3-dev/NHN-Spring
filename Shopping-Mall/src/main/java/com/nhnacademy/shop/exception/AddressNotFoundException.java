package com.nhnacademy.shop.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException() {
        super("Address Not Found!");
    }
}
