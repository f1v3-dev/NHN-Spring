package com.nhnacademy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.domain.Category;
import com.nhnacademy.domain.Inquiry;
import com.nhnacademy.domain.InquiryRegisterRequest;
import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.ValidationFailedException;
import com.nhnacademy.service.InquiryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

@ExtendWith(MockitoExtension.class)
class InquiryRegisterControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    InquiryRegisterController controller;

    @Mock
    InquiryService inquiryService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("문의 등록 페이지")
    void inquiry() throws Exception {
        mockMvc.perform(get("/cs/inquiry"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/inquiryForm"));
    }

    @Test
    @DisplayName("문의 등록 - 실패(@Valid)")
    void inquiryRegister_Fail_Invalid() {
        InquiryRegisterRequest request = new InquiryRegisterRequest(Category.PRAISE, "", "");

        Throwable throwable = catchThrowable(() ->
                mockMvc.perform(post("/cs/inquiry")
                        .param("category", request.getCategory().toString())
                        .param("title", request.getTitle())
                        .param("content", request.getContent())));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }

    @Test
    @DisplayName("문의 등록 - 성공")
    void inquiryRegister_Success() throws Exception {
        InquiryRegisterRequest request = new InquiryRegisterRequest(Category.PRAISE, "문의 테스트", "문의 테스트 내용");
        User user = new User("customer", "1234", "고객", Role.CUSTOMER);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        when(inquiryService.addInquiry(any(), anyString(), anyString(), anyString())).thenReturn(
                Inquiry.create(request.getCategory(), request.getTitle(), request.getContent(), user.getId()));

        MockMultipartFile file = new MockMultipartFile("fileList", "test.jpg", "image/jpeg", "test".getBytes());

        mockMvc.perform(multipart("/cs/inquiry")
                        .file(file)
                        .session(session)
                        .param("category", request.getCategory().name())
                        .param("title", request.getTitle())
                        .param("content", request.getContent()))
                .andExpect(status().is3xxRedirection());

        verify(inquiryService, times(1)).addInquiry(any(), anyString(), anyString(), anyString());
    }

}