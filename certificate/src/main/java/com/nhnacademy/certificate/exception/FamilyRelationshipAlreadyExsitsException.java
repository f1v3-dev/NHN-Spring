package com.nhnacademy.certificate.exception;

public class FamilyRelationshipAlreadyExsitsException extends RuntimeException {
    public FamilyRelationshipAlreadyExsitsException() {
        super("해당 가족관계가 존재하지 않습니다.");
    }
}
