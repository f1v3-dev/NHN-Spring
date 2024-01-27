package com.nhnacademy.springboot.gateway.controller.login;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterRequestDto;
import com.nhnacademy.springboot.gateway.service.AccountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(SignUpController.class)
class SignUpControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Test
    @DisplayName("회원가입 페이지 요청")
    void testGetSignUpForm() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/signup"))
                .andDo(print());
    }


    @Test
    @DisplayName("회원가입 요청 성공")
    void testSingUp_Success() throws Exception {

        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");


        given(accountService.createAccount(any(AccountRegisterRequestDto.class))).willReturn(account);

        mockMvc.perform(post("/signup")
                        .param("name", "정승조")
                        .param("userId", "seungjo")
                        .param("password", "1234")
                        .param("email", "seungjo@nhn.com")
                        .param("phoneNumber", "010-1234-1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 요청 실패")
    void testSingUp_Fail() throws Exception {

        given(accountService.createAccount(any(AccountRegisterRequestDto.class))).willReturn(null);

        mockMvc.perform(post("/signup")
                        .param("name", "정승조")
                        .param("userId", "seungjo")
                        .param("password", "1234")
                        .param("email", "seungjo@nhn.com")
                        .param("phoneNumber", "010-1234-1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/signup"))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 요청 실패 (유효성 검사)")
    void testSignUp_Fail_Validation() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/signup"))
                .andExpect(content().contentType(MediaType.TEXT_HTML + ";charset=UTF-8"))
                .andReturn();

        String viewName = mvcResult.getModelAndView().getViewName();

        Assertions.assertThat(viewName).isEqualTo("error");

    }
}