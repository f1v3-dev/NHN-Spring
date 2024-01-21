package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.exception.CertificateListNotFoundException;
import com.nhnacademy.certificate.exception.HeadOfHouseholdDeletionException;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler({ResidentNotFoundException.class, CertificateListNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(Exception e,
                                          Model model) {

        model.addAttribute("exception", e.getMessage());
        return "error/404";
    }

    @ExceptionHandler(HeadOfHouseholdDeletionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleHeadOfHouseholdDeletionException(Exception e,
                                                         Model model) {

        model.addAttribute("exception", e.getMessage());
        return "error/400";
    }

}
