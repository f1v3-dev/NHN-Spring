package com.nhnacademy.springboot.gateway.controller.task;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskWriteController.class)
class TaskWriteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    MockHttpSession session;

    @BeforeEach
    void setup() {
        session = new MockHttpSession();
        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");

        session.setAttribute("account", account);
    }

    @Test
    @DisplayName("Task 작성 페이지 요청")
    void testGetTaskWriteForm() throws Exception {

        given(taskService.getTagList(anyLong())).willReturn(null);
        given(taskService.getMilestoneList(anyLong())).willReturn(null);

        mockMvc.perform(get("/task/write/{projectId}", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("registrant"))
                .andExpect(view().name("task/write"))
                .andDo(print());
    }

    @Test
    @DisplayName("Task 등록 요청 - 성공")
    void testWriteTask_Success() throws Exception {

        mockMvc.perform(post("/task/write/{projectId}", 1L)
                        .session(session)
                        .param("registrant", "seungjo")
                        .param("taskName", "test task")
                        .param("taskDetails", "test task details"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1"));
    }

    @Test
    @DisplayName("Task 등록 요청 - 유효성 검사 실패")
    void testWriteTask_ValidationFailed() throws Exception {
        mockMvc.perform(post("/task/write/{projectId}", 1L)
                        .session(session))
                .andExpect(view().name("error"));
    }
}