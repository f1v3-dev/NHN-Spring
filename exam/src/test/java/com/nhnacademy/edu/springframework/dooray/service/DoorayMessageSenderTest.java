package com.nhnacademy.edu.springframework.dooray.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;

import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.dooray.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/beans.xml")
class DoorayMessageSenderTest {

    @InjectMocks
    private DoorayMessageSender messageSender;

    @Mock
    private DoorayHookSender hookSender;

    private User user;

    private String message;

    @BeforeEach
    void setup() {
        user = new User("정승조");
        message = "안녕하세요";
        MockitoAnnotations.initMocks(this);
    }


    @Test
    @DisplayName("두레이 메시지 보내기 테스트 - 성공")
    void testDoorayMessageSender() {
        doNothing().when(hookSender).send(any());

        boolean actual = messageSender.sendMessage(user, message);
        Assertions.assertThat(actual).isTrue();
    }
}