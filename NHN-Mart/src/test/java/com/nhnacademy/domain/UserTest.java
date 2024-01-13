package com.nhnacademy.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("생성자 테스트")
    void constructor() {
        User user = new User("tester", "1234", "테스터", Role.CUSTOMER);

        assertEquals("tester", user.getId());
        assertEquals("1234", user.getPassword());
        assertEquals("테스터", user.getName());
        assertEquals(Role.CUSTOMER, user.getRole());
    }
}