package com.nhnacademy.springboot.gateway.controller.login;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(LogoutController.class)
class LogoutControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    @DisplayName("로그아웃 성공")
    void testLogoutSuccess() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("account", "account_info");

        mockMvc.perform(MockMvcRequestBuilders.get("/logout")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"))
                .andDo(print());
    }
}