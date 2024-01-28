package com.nhnacademy.springboot.gateway.controller.project.milestone;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.milestone.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MilestoneListController.class)
class MilestoneListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    MockHttpSession session = new MockHttpSession();

    @BeforeEach
    void setup() {
        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");

        session.setAttribute("account", account);
    }

    @Test
    @DisplayName("프로젝트 마일스톤 리스트 조회")
    void testGetMilestoneListByProjectId() throws Exception {

        List<MilestoneDto> milestoneList
                = List.of(
                        new MilestoneDto(1L, "마일스톤1", Date.valueOf("2024-01-02"), Date.valueOf("2024-01-06")),
                        new MilestoneDto(2L, "마일스톤2", Date.valueOf("2024-01-02"), Date.valueOf("2024-01-14"))
        );

        given(taskService.getMilestoneList(anyLong())).willReturn(milestoneList);


        mockMvc.perform(get("/project/{projectId}/milestone", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("project/milestone/milestone-list"))
                .andExpect(model().attributeExists("milestoneList"))
                .andDo(print());
    }


}