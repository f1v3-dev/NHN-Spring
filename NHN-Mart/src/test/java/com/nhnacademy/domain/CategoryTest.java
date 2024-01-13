package com.nhnacademy.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    @DisplayName("생성자 테스트")
    void constructor() {
        Category category = Category.COMPLAINT;

        assertThat(category.getCategoryName()).isEqualTo("불만 접수");
        assertThat(category.name()).isEqualTo("COMPLAINT");
    }

}