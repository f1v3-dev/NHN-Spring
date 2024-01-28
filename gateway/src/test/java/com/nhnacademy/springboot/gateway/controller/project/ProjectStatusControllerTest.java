package com.nhnacademy.springboot.gateway.controller.project;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProjectStatusController.class)
class ProjectStatusControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    MockHttpSession session = new MockHttpSession();

    @BeforeEach
    void setUp() {
        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");


        session.setAttribute("account", account);
    }


    @Test
    @DisplayName("프로젝트 상태 페이지 요청")
    void testGetProjectStatus() throws Exception {

        mockMvc.perform(get("/project/{projectId}/status", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attribute("projectId", 1L))
                .andExpect(view().name("project/status"));
    }

    @Test
    @DisplayName("프로젝트 상태 변경 - 성공")
    void testEditProjectStatus() throws Exception {

        mockMvc.perform(put("/project/{projectId}/status", 1L)
                        .session(session)
                        .param("statusId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1"));
    }
}