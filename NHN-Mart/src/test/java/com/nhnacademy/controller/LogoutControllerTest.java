package com.nhnacademy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class LogoutControllerTest {

    MockMvc mockMvc;

    LogoutController controller;

    @BeforeEach
    void setUp() {
        controller = new LogoutController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("로그아웃 - 로그인 세션이 있는 경우")
    void logout_hasSession() throws Exception {
        User user = new User("customer", "1234", "고객", Role.CUSTOMER);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", user);

        mockMvc.perform(get("/logout")
                        .session(session))
                .andExpect(view().name("redirect:/"))
                .andDo(print());
    }

    @Test
    @DisplayName("로그아웃 - 로그인 세션이 없는 경우")
    void logout_Has_Not_Session() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(view().name("redirect:/login"))
                .andDo(print());
    }
}