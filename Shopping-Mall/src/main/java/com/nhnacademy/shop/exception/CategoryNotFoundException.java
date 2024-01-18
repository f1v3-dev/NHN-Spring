package com.nhnacademy.shop.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("Category Not Found");
    }
}
