package com.nhnacademy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.domain.Category;
import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.InquiryNotFoundException;
import com.nhnacademy.service.InquiryService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

@ExtendWith(MockitoExtension.class)
class InquiryControllerTest {

    private String UPLOAD_DIR = "/Users/seungjo/Desktop/NHN-Spring/NHN-Mart/src/main/webapp/WEB-INF/img/";

    MockMvc mockMvc;

    @InjectMocks
    InquiryController inquiryController;

    @Mock
    InquiryService inquiryService;

    User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(inquiryController).build();

        user = new User("customer", "1234", "고객", Role.CUSTOMER);
    }

    @Test
    @DisplayName("문의 리스트 페이지 조회")
    void inquiryList() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        List<Inquiry> inquiryList = List.of(
                Inquiry.create(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer"),
                Inquiry.create(Category.SUGGESTION, "문의 테스트2", "문의 테스트 내용2", "customer"));

        when(inquiryService.findInquiryListByUserId(anyString())).thenReturn(inquiryList);

        mockMvc.perform(get("/cs")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/inquiryList"))
                .andExpect(model().attributeExists("inquiryList"))
                .andReturn();
    }

    @Test
    @DisplayName("문의 리스트 페이지 조회 - 카테고리 선정")
    void inquiryList_With_Category() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        List<Inquiry> inquiryList = List.of(
                Inquiry.create(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer"),
                Inquiry.create(Category.PRAISE, "문의 테스트2", "문의 테스트 내용2", "customer"));

        when(inquiryService.findInquiryListByUserIdAndCategory(anyString(), anyString())).thenReturn(inquiryList);

        mockMvc.perform(get("/cs")
                        .session(session)
                        .param("category", "PRAISE"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/inquiryList"))
                .andExpect(model().attributeExists("inquiryList"))
                .andReturn();
    }

    @Test
    @DisplayName("문의 상세 조회 - 실패(존재하지 않는 문의사항)")
    void viewInquiry_NotFound() {
        when(inquiryService.isExists(anyLong())).thenReturn(false);

        Throwable throwable = catchThrowable(() ->
                mockMvc.perform(get("/cs/inquiry/{inquiryId}", 1)));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(InquiryNotFoundException.class);
    }

    @Test
    @DisplayName("문의 상세 조회 - 성공")
    void viewInquiry_Success() throws Exception {
        when(inquiryService.isExists(anyLong())).thenReturn(true);

        Inquiry inquiry = Inquiry.create(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer");
        when(inquiryService.findById(anyLong())).thenReturn(inquiry);

        ReflectionTestUtils.setField(inquiryController, "UPLOAD_DIR", UPLOAD_DIR);

        mockMvc.perform(get("/cs/inquiry/{inquiryId}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/inquiryView"))
                .andExpect(model().attributeExists("inquiry"))
                .andDo(print());
    }

}