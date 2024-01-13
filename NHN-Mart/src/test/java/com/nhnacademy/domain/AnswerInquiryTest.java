package com.nhnacademy.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerInquiryTest {

    @Test
    @DisplayName("생성자 테스트")
    void constructor() {
        AnswerInquiry answerInquiry = new AnswerInquiry("답변 테스트입니다.");

        assertThat(answerInquiry.getAnswer()).isEqualTo("답변 테스트입니다.");
    }

}