package com.nhnacademy.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.domain.Category;
import com.nhnacademy.domain.Inquiry;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InquiryRepositoryTest {


    private InquiryRepository inquiryRepository;

    @BeforeEach
    void setUp() {
        inquiryRepository = new InquiryRepositoryImpl();
    }

    @Test
    @DisplayName("문의사항 등록")
    void addInquiry() {
        Inquiry addedInquiry = inquiryRepository.addInquiry(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer");

        assertThat(addedInquiry.getId()).isEqualTo(1L);
        assertThat(addedInquiry.getCategory()).isEqualTo(Category.PRAISE);
        assertThat(addedInquiry.getTitle()).isEqualTo("문의 테스트");
        assertThat(addedInquiry.getContent()).isEqualTo("문의 테스트 내용");
        assertThat(addedInquiry.getWriterId()).isEqualTo("customer");
    }

    @Test
    @DisplayName("문의사항 리스트 조회")
    void getInquiryList() {
        inquiryRepository.addInquiry(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer");
        inquiryRepository.addInquiry(Category.ETC, "문의 테스트2", "문의 테스트 내용2", "customer");

        List<Inquiry> inquiryList = inquiryRepository.getInquiryList("customer");

        assertThat(inquiryList).hasSize(2);
        assertThat(inquiryList.get(0).getTitle()).isEqualTo("문의 테스트");
        assertThat(inquiryList.get(1).getTitle()).isEqualTo("문의 테스트2");
    }

    @Test
    @DisplayName("문의 사항 조회")
    void getInquiry_Success() {

        Inquiry addedInquiry = inquiryRepository.addInquiry(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer");

        Inquiry getInquiry = inquiryRepository.getInquiry(1L);

        assertThat(getInquiry).isEqualTo(addedInquiry);
    }

    @Test
    @DisplayName("문의 사항 조회 실패")
    void getInquiry_Fail() {
        Inquiry getInquiry = inquiryRepository.getInquiry(1L);
        assertThat(getInquiry).isNull();
    }

    @Test
    @DisplayName("문의 존재 - True")
    void exists_Return_True() {
        inquiryRepository.addInquiry(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer");

        boolean exists = inquiryRepository.exists(1L);
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("문의 존재 - False")
    void exists_Return_False() {

        boolean exists = inquiryRepository.exists(1L);
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("모든 문의사항 조회")
    void getAllInquiry() {
        inquiryRepository.addInquiry(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer");
        inquiryRepository.addInquiry(Category.ETC, "문의 테스트2", "문의 테스트 내용2", "customer");

        Map<Long, Inquiry> allInquiry = inquiryRepository.getAllInquiry();

        assertThat(allInquiry).hasSize(2);
        assertThat(allInquiry.get(1L).getWriterId()).isEqualTo("customer");
    }

    @Test
    @DisplayName("문의 답변")
    void answer() {
        inquiryRepository.addInquiry(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer");

        inquiryRepository.answer(1L, "답변 테스트", "관리자");

        Inquiry inquiry = inquiryRepository.getInquiry(1L);
        assertThat(inquiry.isAnswered()).isTrue();
        assertThat(inquiry.getAnswerContent()).isEqualTo("답변 테스트");
        assertThat(inquiry.getAnswerWriterName()).isEqualTo("관리자");
    }
}