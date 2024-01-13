package com.nhnacademy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class HomeControllerTest {

    HomeController controller;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        controller = new HomeController();

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Index 페이지 - 로그인 전")
    void index_beforeLogin() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    @Test
    @DisplayName("Index 페이지 - 로그인(customer)")
    void index_Login_Customer() throws Exception {

        User customer = new User("customer", "1234", "고객", Role.CUSTOMER);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", customer);

        mockMvc.perform(get("/")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/home"));
    }

    @Test
    @DisplayName("Index 페이지 - 로그인(admin)")
    void index_Login_Admin() throws Exception {
        User admin = new User("admin", "12345", "관리자", Role.ADMIN);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", admin);

        mockMvc.perform(get("/")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/home"));
    }
}