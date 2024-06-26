package com.nhnacademy.springboot.gateway.controller.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MyPageController.class)
class MyPageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("마이페이지 요청")
    void testGetMyPage() throws Exception {

        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("account", account);


        mockMvc.perform(get("/mypage")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("user/my-page"))
                .andExpect(model().attribute("account", account))
                .andDo(print());
    }
}