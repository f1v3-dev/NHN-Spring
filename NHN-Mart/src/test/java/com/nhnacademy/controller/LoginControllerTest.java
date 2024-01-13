package com.nhnacademy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import com.nhnacademy.domain.UserLoginRequest;
import com.nhnacademy.exception.ValidationFailedException;
import com.nhnacademy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    MockMvc mockMvc;
    @InjectMocks
    LoginController controller;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("로그인 페이지 - 로그인을 하지 않은 경우")
    void loginForm() throws Exception {

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"));
    }

    @Test
    @DisplayName("로그인 페이지 - 로그인을 한 경우")
    void loginForm_Customer() throws Exception {
        User customer = new User("customer", "1234", "고객", Role.CUSTOMER);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", customer);

        mockMvc.perform(get("/login")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("로그인 시도 - 로그인 실패(@Valid)")
    void doLogin_Invalid() {
        UserLoginRequest request = new UserLoginRequest("", "");

        Throwable throwable = catchThrowable(() ->
                mockMvc.perform(post("/login")
                        .param("id", request.getId())
                        .param("password", request.getPassword())));

        assertThat(throwable).isInstanceOf(NestedServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }

    @Test
    @DisplayName("로그인 시도 - 성공")
    void doLogin_NotMatch() throws Exception {

        User user = new User("customer", "1234", "고객", Role.CUSTOMER);
        when(userService.doLogin(anyString(), anyString())).thenReturn(user);

        mockMvc.perform(post("/login")
                        .param("id", "customer")
                        .param("password", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}