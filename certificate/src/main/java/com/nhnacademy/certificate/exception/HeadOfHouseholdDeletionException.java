package com.nhnacademy.certificate.exception;

public class HeadOfHouseholdDeletionException extends RuntimeException {

    public HeadOfHouseholdDeletionException() {
        super("세대주는 삭제할 수 없습니다.");
    }
}
