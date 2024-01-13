package com.nhnacademy.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InquiryTest {

    @Test
    @DisplayName("생성자 테스트")
    void constructor() {
        Inquiry inquiry =
                new Inquiry(Category.ETC, "문의 생성자 테스트", "문의 생성자 테스트 내용", LocalDateTime.now(), "customer", false);

        assertThat(inquiry.getCategory()).isEqualTo(Category.ETC);
        assertThat(inquiry.getTitle()).isEqualTo("문의 생성자 테스트");
        assertThat(inquiry.getContent()).isEqualTo("문의 생성자 테스트 내용");
        assertThat(inquiry.getCreatedDate()).isNotNull();
        assertThat(inquiry.getWriterId()).isEqualTo("customer");
        assertThat(inquiry.isAnswered()).isFalse();
    }

    @Test
    @DisplayName("create 메서드 테스트")
    void create() {
        Inquiry inquiry = Inquiry.create(Category.PRAISE, "테스트", "테스트입니다.", "customer");

        assertThat(inquiry.getCategory()).isEqualTo(Category.PRAISE);
        assertThat(inquiry.getTitle()).isEqualTo("테스트");
        assertThat(inquiry.getContent()).isEqualTo("테스트입니다.");
        assertThat(inquiry.getWriterId()).isEqualTo("customer");

        assertThat(inquiry.getCreatedDate()).isNotNull();
        assertThat(inquiry.isAnswered()).isFalse();
    }

    @Test
    @DisplayName("답변 테스트 (Setter)")
    void answerInquiry() {
        Inquiry inquiry = Inquiry.create(Category.PRAISE, "테스트", "테스트입니다.", "customer");

        inquiry.setId(1L);
        inquiry.setAnswered(true);
        inquiry.setAnsweredDate(LocalDateTime.now());
        inquiry.setAnswerWriterName("admin");
        inquiry.setAnswerContent("답변 테스트입니다.");

        assertThat(inquiry.getId()).isEqualTo(1L);
        assertThat(inquiry.isAnswered()).isTrue();
        assertThat(inquiry.getAnswerContent()).isEqualTo("답변 테스트입니다.");
        assertThat(inquiry.getAnsweredDate()).isNotNull();
        assertThat(inquiry.getAnswerWriterName()).isEqualTo("admin");
    }


}