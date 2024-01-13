package com.nhnacademy.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InquiryRegisterRequestTest {

    @Test
    @DisplayName("생성자 테스트")
    void constructor() {
        InquiryRegisterRequest inquiryRegisterRequest =
                new InquiryRegisterRequest(Category.PRAISE, "문의 요청 테스트", "문의 요청 테스트 내용");

        assertThat(inquiryRegisterRequest.getCategory()).isEqualTo(Category.PRAISE);
        assertThat(inquiryRegisterRequest.getTitle()).isEqualTo("문의 요청 테스트");
        assertThat(inquiryRegisterRequest.getContent()).isEqualTo("문의 요청 테스트 내용");
    }
}