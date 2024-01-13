package com.nhnacademy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.domain.AnswerInquiry;
import com.nhnacademy.domain.Category;
import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.InquiryNotFoundException;
import com.nhnacademy.exception.ValidationFailedException;
import com.nhnacademy.service.InquiryService;
import java.util.List;
import java.util.Optional;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

@ExtendWith(MockitoExtension.class)
class AdminInquiryControllerTest {

    private String UPLOAD_DIR = "/Users/seungjo/Desktop/NHN-Spring/NHN-Mart/src/main/webapp/WEB-INF/img/";

    private MockMvc mockMvc;

    @InjectMocks
    private AdminInquiryController controller;

    @Mock
    private InquiryService inquiryService;


    Inquiry inquiry;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        inquiry = Inquiry.create(Category.PRAISE, "문의 테스트", "문의 테스트 내용", "customer");
    }

    @Test
    @DisplayName("관리자 문의사항 목록 조회")
    void adminInquiry() throws Exception {
        List<Inquiry> inquiryList = List.of(inquiry);
        when(inquiryService.findNotAnsweredInquiryList()).thenReturn(inquiryList);

        MvcResult mvcResult =
                mockMvc.perform(get("/admin/cs")).andExpect(status().isOk()).andExpect(view().name("admin/inquiryList"))
                        .andReturn();

        Optional<List> optionalList =
                Optional.ofNullable(mvcResult.getModelAndView().getModel()).map(m -> m.get("inquiryList"))
                        .map(List.class::cast);

        assertThat(optionalList).isPresent();
        assertThat(optionalList.get()).hasSize(1);
        assertThat(optionalList.get().get(0)).isEqualTo(inquiry);

        verify(inquiryService, times(1)).findNotAnsweredInquiryList();
    }

    @Test
    @DisplayName("관리자 문의사항 상세 조회 - 실패(존재하지 않는 문의사항)")
    void adminInquiry_Fail() {
        when(inquiryService.isExists(anyLong())).thenReturn(false);

        Throwable throwable = catchThrowable(() -> mockMvc.perform(get("/admin/cs/inquiry/{inquiryId}", 1)));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(InquiryNotFoundException.class);

        verify(inquiryService, times(1)).isExists(anyLong());
    }

    @Test
    @DisplayName("관리자 문의사항 상세 조회 - 성공")
    void adminInquiry_Success() throws Exception {

        when(inquiryService.isExists(anyLong())).thenReturn(true);
        when(inquiryService.findById(anyLong())).thenReturn(inquiry);

        ReflectionTestUtils.setField(controller, "UPLOAD_DIR", UPLOAD_DIR);

        mockMvc.perform(get("/admin/cs/inquiry/{inquiryId}", 1)).andExpect(status().isOk())
                .andExpect(view().name("admin/inquiryAnswerForm")).andDo(print());

        verify(inquiryService, times(1)).isExists(anyLong());
        verify(inquiryService, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("문의사항 답변 등록 - 실패 (@Valid)")
    void answerInquiry_Fail_Invalid() {
        AnswerInquiry answer = new AnswerInquiry("");

        Throwable throwable = catchThrowable(
                () -> mockMvc.perform(put("/admin/cs/inquiry/{inquiryId}", 1).param("answer", answer.getAnswer())));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }

    @Test
    @DisplayName("문의사항 답변 등록 - 실패(존재하지 않는 문의사항)")
    void answerInquiry_Fail_Not_Found() {
        AnswerInquiry answer = new AnswerInquiry("답변 테스트");
        when(inquiryService.isExists(anyLong())).thenReturn(false);

        Throwable throwable = catchThrowable(
                () -> mockMvc.perform(put("/admin/cs/inquiry/{inquiryId}", 1).param("answer", answer.getAnswer())));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(InquiryNotFoundException.class);

        verify(inquiryService, times(1)).isExists(anyLong());
    }

    @Test
    @DisplayName("문의사항 답변 등록 - 성공")
    void answerInquiry_Success() throws Exception {
        AnswerInquiry answer = new AnswerInquiry("답변 테스트");

        User admin = new User("admin", "12345", "관리자", Role.ADMIN);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", admin);

        when(inquiryService.isExists(anyLong())).thenReturn(true);
        doNothing().when(inquiryService).answer(anyLong(), anyString(), anyString());

        mockMvc.perform(put("/admin/cs/inquiry/{inquiryId}", 1).param("answer", answer.getAnswer()).session(session))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/admin/cs")).andDo(print());

        verify(inquiryService, times(1)).answer(anyLong(), anyString(), anyString());
    }

}