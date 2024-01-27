package com.nhnacademy.springboot.gateway.controller.login;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.springboot.gateway.domain.account.AccountDeleteResponse;
import com.nhnacademy.springboot.gateway.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WithdrawalController.class)
class WithdrawalControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @Test
    @DisplayName("회원탈퇴 성공")
    void testDeleteAccount() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("account", "account_info");


        AccountDeleteResponse response = new AccountDeleteResponse("OK");
        given(accountService.deleteAccount(anyLong())).willReturn(response);

        mockMvc.perform(delete("/account/{id}", 1L)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andDo(print());
    }

    @Test
    @DisplayName("회원탈퇴 실패")
    void testDeleteAccount_Fail() throws Exception {

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("account", "account_info");

        AccountDeleteResponse response = new AccountDeleteResponse("FAIL");
        given(accountService.deleteAccount(anyLong())).willReturn(response);

        mockMvc.perform(delete("/account/{id}", 100L)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mypage"))
                .andDo(print());
    }

}