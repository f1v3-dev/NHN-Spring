package com.nhnacademy.controller;

import com.nhnacademy.exception.UserNotFoundException;
import com.nhnacademy.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException exception, Model model) {

        model.addAttribute("exception", exception.getMessage());
        return "error/404";
    }

    @ExceptionHandler(ValidationFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationFailedException(ValidationFailedException exception, Model model) {

        model.addAttribute("exception", exception.getMessage());
        return "error/400";
    }
}
