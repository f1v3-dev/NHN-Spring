package com.nhnacademy.springboot.gateway.controller.task;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskModuleResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskUpdateController.class)
class TaskUpdateControllerTest {

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
    @DisplayName("업무 수정 페이지 요청")
    void testGetTaskUpdateForm() throws Exception {

        TaskModuleResponse task
                = new TaskModuleResponse(1L, "task 1", "task 1 detail", "seungjo", null, null, null);

        given(taskService.getTask(anyLong()))
                .willReturn(task);

        given(taskService.getTagList(anyLong()))
                .willReturn(List.of());

        given(taskService.getMilestoneList(anyLong()))
                .willReturn(List.of());


        mockMvc.perform(get("/project/{projectId}/task/{taskId}/update", 1L, 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("tagList"))
                .andExpect(model().attributeExists("milestoneList"))
                .andExpect(model().attributeExists("task"))
                .andExpect(model().attributeExists("projectId"))
                .andExpect(status().isOk())
                .andExpect(view().name("task/update"));
    }

    @Test
    @DisplayName("업무 수정 - 성공")
    void testUpdateTask_Success() throws Exception {

        mockMvc.perform(put("/project/{projectId}/task/{taskId}/update", 1L, 1L)
                        .session(session)
                        .param("taskName", "task 1")
                        .param("taskDetails", "task 1 detail")
                        .param("registrant", "seungjo"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1/task/1"));
    }

    @Test
    @DisplayName("업무 수정 - 실패 (유효성 검사)")
    void testUpdateTask_Fail() throws Exception {

        mockMvc.perform(put("/project/{projectId}/task/{taskId}/update", 1L, 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }
}