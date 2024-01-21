package com.nhnacademy.certificate.exception;

public class CertificateListNotFoundException extends RuntimeException{

    public CertificateListNotFoundException() {
        super("This resident has any certificate.");
    }
}
