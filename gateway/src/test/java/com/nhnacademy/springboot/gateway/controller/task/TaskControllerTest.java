package com.nhnacademy.springboot.gateway.controller.task;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskModuleResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

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
    @DisplayName("Task 페이지 요청")
    void testGetTaskList() throws Exception {

        TaskModuleResponse task
                = new TaskModuleResponse(1L, "task 1", "task 1 detail", "seungjo", null, null, null);

        given(taskService.getTask(anyLong()))
                .willReturn(task);

        mockMvc.perform(get("/project/{projectId}/task/{taskId}", 1L, 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("task/main"));
    }

}