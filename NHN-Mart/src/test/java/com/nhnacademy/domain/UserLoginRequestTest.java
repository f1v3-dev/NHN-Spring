package com.nhnacademy.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserLoginRequestTest {

    @Test
    @DisplayName("생성자 테스트")
    void constructor() {
        UserLoginRequest user = new UserLoginRequest("tester", "12345");

        assertThat(user.getId()).isEqualTo("tester");
        assertThat(user.getPassword()).isEqualTo("12345");
    }

}