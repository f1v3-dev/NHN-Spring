package com.nhnacademy.springboot.gateway.controller.advice;

import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler({ValidationFailedException.class})
    public String handleValidationException(ValidationFailedException exception,
                                            Model model) {

        model.addAttribute("error", exception.getMessage());

        return "error";
    }
}
