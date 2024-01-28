package com.nhnacademy.springboot.gateway.controller.login;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.service.AccountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Test
    @DisplayName("로그인 페이지 요청")
    void testGetLoginForm() throws Exception {

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login"))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인한 유저가 로그인 페이지 요청")
    void testGetLoginFormByLoginUser() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("account", "account_info");

        mockMvc.perform(get("/login").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 성공")
    void testDoLoginSuccess() throws Exception {


        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");

        given(accountService.matches(any(AccountLoginRequestDto.class))).willReturn(account);

        mockMvc.perform(post("/login")
                        .param("userId", "seungjo")
                        .param("password", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 실패 (존재하지 않는 회원)")
    void testDoLogin_Fail_NotExistAccount() throws Exception {

        given(accountService.matches(any(AccountLoginRequestDto.class))).willReturn(null);

        mockMvc.perform(post("/login")
                        .param("userId", "seungjo")
                        .param("password", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andDo(print());
    }


    @Test
    @DisplayName("로그인 요청 (유효성 검사 실패)")
    void testDoLogin() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/login"))
                .andExpect(content().contentType(MediaType.TEXT_HTML + ";charset=UTF-8"))
                .andDo(print())
                .andReturn();

        String viewName = mvcResult.getModelAndView().getViewName();

        Assertions.assertThat(viewName).isEqualTo("error");
    }

}