package com.nhnacademy.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.nhnacademy.domain.Category;
import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.repository.InquiryRepository;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InquiryServiceImplTest {

    @InjectMocks
    InquiryServiceImpl inquiryService;

    @Mock
    InquiryRepository inquiryRepository;

    @Test
    @DisplayName("User ID로 사용자 문의 조회")
    void findInquiryListByUserId() {

        List<Inquiry> list = List.of(
                Inquiry.create(Category.PRAISE, "test1", "test1", "tester"),
                Inquiry.create(Category.COMPLAINT, "test2", "test2", "tester"),
                Inquiry.create(Category.ETC, "test3", "test3", "tester"));

        when(inquiryRepository.getInquiryList(anyString())).thenReturn(list);

        List<Inquiry> inquiryList = inquiryService.findInquiryListByUserId("tester");

        assertThat(inquiryList).hasSize(3);
        assertThat(inquiryList.get(0).getCategory()).isEqualByComparingTo(Category.PRAISE);
        assertThat(inquiryList.get(1).getCategory()).isEqualByComparingTo(Category.COMPLAINT);
        assertThat(inquiryList.get(2).getCategory()).isEqualByComparingTo(Category.ETC);
    }

    @Test
    @DisplayName("Inquiry ID로 문의 조회 - 성공")
    void findById_Success() {
        Inquiry inquiry = Inquiry.create(Category.PRAISE, "test1", "test1", "tester");
        inquiry.setId(1L);

        when(inquiryRepository.getInquiry(anyLong())).thenReturn(inquiry);

        Inquiry findInquiry = inquiryService.findById(1L);

        assertThat(findInquiry.getCategory()).isEqualByComparingTo(Category.PRAISE);
        assertThat(findInquiry.getId()).isEqualTo(1L);
        assertThat(findInquiry.getTitle()).isEqualTo("test1");
        assertThat(findInquiry.getContent()).isEqualTo("test1");
        assertThat(findInquiry.getWriterId()).isEqualTo("tester");
    }

    @Test
    @DisplayName("Inquiry ID로 문의 조회 - 실패")
    void findById_Fail() {

        when(inquiryRepository.getInquiry(anyLong())).thenReturn(null);

        Inquiry findInquiry = inquiryService.findById(1L);

        assertThat(findInquiry).isNull();
    }

    @Test
    @DisplayName("Inquiry ID로 존재 여부 확인")
    void existsById() {
        when(inquiryRepository.exists(anyLong())).thenReturn(true);

        boolean exists = inquiryService.isExists(1L);

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("User ID와 Category로 문의 조회")
    void findInquiryListByUserIdAndCategory() {
        List<Inquiry> list = List.of(
                Inquiry.create(Category.PRAISE, "test1-1", "test1-1", "tester"),
                Inquiry.create(Category.COMPLAINT, "test1-2", "test1-2", "tester"),
                Inquiry.create(Category.ETC, "test2-1", "test2-1", "tester2"),
                Inquiry.create(Category.ETC, "test3-1", "test3-1", "tester3"));

        when(inquiryRepository.getInquiryList(anyString())).thenReturn(list);

        List<Inquiry> inquiryList = inquiryService.findInquiryListByUserIdAndCategory("tester", "PRAISE");

        assertThat(inquiryList).hasSize(1);
        assertThat(inquiryList.get(0).getCategory()).isEqualByComparingTo(Category.PRAISE);
    }

    @Test
    @DisplayName("미답변 문의 조회")
    void findNotAnsweredInquiryList() {
        HashMap<Long, Inquiry> inquiryMap = new HashMap<>();
        inquiryMap.put(1L, Inquiry.create(Category.PRAISE, "test1-1", "test1-1", "tester"));
        inquiryMap.put(2L, Inquiry.create(Category.COMPLAINT, "test1-2", "test1-2", "tester"));
        inquiryMap.put(3L, Inquiry.create(Category.ETC, "test2-1", "test2-1", "tester2"));
        inquiryMap.put(4L, Inquiry.create(Category.ETC, "test3-1", "test3-1", "tester3"));

        when(inquiryRepository.getAllInquiry()).thenReturn(inquiryMap);

        List<Inquiry> inquiryList = inquiryService.findNotAnsweredInquiryList();

        assertThat(inquiryList).hasSize(4);
        assertThat(inquiryList.get(0).getCategory()).isEqualByComparingTo(Category.PRAISE);
        assertThat(inquiryList.get(1).getCategory()).isEqualByComparingTo(Category.COMPLAINT);
        assertThat(inquiryList.get(2).getCategory()).isEqualByComparingTo(Category.ETC);
        assertThat(inquiryList.get(3).getCategory()).isEqualByComparingTo(Category.ETC);
    }

    @Test
    @DisplayName("문의 답변")
    void answer() {
        Inquiry inquiry = Inquiry.create(Category.PRAISE, "test1-1", "test1-1", "tester");
        inquiry.setId(1L);

        doNothing().when(inquiryRepository).answer(anyLong(), anyString(), anyString());

        inquiryService.answer(1L, "대답 테스트", "관리자");

        Mockito.verify(inquiryRepository, Mockito.times(1)).answer(anyLong(), anyString(), anyString());
    }

    @Test
    @DisplayName("문의 등록")
    void addInquiry() {
        when(inquiryRepository.addInquiry(any(), anyString(), anyString(), anyString())).thenReturn(
                Inquiry.create(Category.PRAISE, "test1", "test1", "tester"));

        Inquiry inquiry = inquiryService.addInquiry(Category.PRAISE, "test1", "test1", "tester");

        assertThat(inquiry.getCategory()).isEqualByComparingTo(Category.PRAISE);
        assertThat(inquiry.getTitle()).isEqualTo("test1");
        assertThat(inquiry.getContent()).isEqualTo("test1");
        assertThat(inquiry.getWriterId()).isEqualTo("tester");
    }
}