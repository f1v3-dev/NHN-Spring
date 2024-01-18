package com.nhnacademy.shop.exception;

public class CategoryUsedProductException extends RuntimeException {

    public CategoryUsedProductException() {
        super("해당 카테고리를 사용하는 상품이 존재합니다.");
    }

}
