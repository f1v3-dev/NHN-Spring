package com.nhnacademy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.nhnacademy.exception.InquiryNotFoundException;
import com.nhnacademy.exception.UserNotFoundException;
import com.nhnacademy.exception.ValidationFailedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@ExtendWith(MockitoExtension.class)
class WebControllerAdviceTest {

    @InjectMocks
    private WebControllerAdvice webControllerAdvice;

    @Mock
    private Model model;


    @Test
    @DisplayName("UserNotFoundException")
    void userNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException();
        String view = webControllerAdvice.handleNotFoundException(exception, model);

        assertThat(view).isEqualTo("error/404");

        verify(model).addAttribute("exception", exception.getMessage());
    }

    @Test
    @DisplayName("InquiryNotFoundException")
    void inquiryNotFoundException() {
        InquiryNotFoundException exception = new InquiryNotFoundException();
        String view = webControllerAdvice.handleNotFoundException(exception, model);

        assertThat(view).isEqualTo("error/404");

        verify(model).addAttribute("exception", exception.getMessage());
    }

    @Test
    @DisplayName("ValidationFailedException")
    void validationFailedException() {
        BindingResult bindingResult = mock(BindingResult.class);
        ValidationFailedException exception = new ValidationFailedException(bindingResult);

        String view = webControllerAdvice.handleValidationFailedException(exception, model);

        assertThat(view).isEqualTo("error/400");

        verify(model).addAttribute("exception", exception.getMessage());

    }
}