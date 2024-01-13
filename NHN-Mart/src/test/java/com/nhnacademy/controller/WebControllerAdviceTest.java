package com.nhnacademy.controller;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WebControllerAdviceTest {

    WebControllerAdvice advice;

    @BeforeEach
    void setUp() {
        advice = new WebControllerAdvice();
    }

    @Test
    @DisplayName("UserNotFoundException")
    void handle_UserNotFoundException() {




    }

}