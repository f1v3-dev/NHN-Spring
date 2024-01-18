package com.nhnacademy.shop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler
    public String handleException(Exception ex, Model model) {

        model.addAttribute("exception", ex.getMessage());
        return "error";
    }
}
