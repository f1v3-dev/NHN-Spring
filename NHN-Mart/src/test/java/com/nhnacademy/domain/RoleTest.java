package com.nhnacademy.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoleTest {

    @Test
    @DisplayName("생성자 테스트")
    void constructor() {
        Role role = Role.ADMIN;

        assertThat(role.name()).isEqualTo("ADMIN");
    }

}