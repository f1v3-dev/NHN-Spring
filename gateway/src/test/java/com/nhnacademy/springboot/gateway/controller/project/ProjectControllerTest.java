package com.nhnacademy.springboot.gateway.controller.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectListRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.project.Status;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskListModule;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskListResponse;
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

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

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
    @DisplayName("프로젝트 메인 페이지 요청")
    void testGetProject() throws Exception {

        TaskListResponse taskList = new TaskListResponse(
                new ProjectDto(1L, "프로젝트1", "프로젝트1", new Status(3L, "종료")),
                List.of(new TaskListModule()));


        given(taskService.getTaskList(anyLong()))
                .willReturn(taskList);

        mockMvc.perform(get("/project/{projectId}", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("project/main"))
                .andExpect(model().attributeExists("projectId"))
                .andExpect(model().attributeExists("taskList"));
    }

}