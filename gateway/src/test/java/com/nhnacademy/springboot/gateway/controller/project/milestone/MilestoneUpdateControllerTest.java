package com.nhnacademy.springboot.gateway.controller.project.milestone;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.sql.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MilestoneUpdateController.class)
class MilestoneUpdateControllerTest {

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
    @DisplayName("프로젝트 마일스톤 수정 폼 요청")
    void testGetMilestoneUpdateForm() throws Exception {

        MilestoneDto milestoneDto = new MilestoneDto(1L, "마일스톤1", Date.valueOf("2024-01-01"), Date.valueOf("2024-01-31") );

        given(taskService.getMilestone(anyLong())).willReturn(milestoneDto);

        mockMvc.perform(get("/project/{projectId}/milestone/update/{milestoneId}", 1L, 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("project/milestone/milestone-update"))
                .andExpect(model().attributeExists("milestone"))
                .andExpect(model().attributeExists("projectId"))
                .andDo(print());
    }

    @Test
    @DisplayName("프로젝트 마일스톤 수정 - 성공")
    void testUpdateMilestone_Success() throws Exception {

        mockMvc.perform(put("/project/{projectId}/milestone/update", 1L, 1L)
                        .session(session)
                        .param("milestoneId", "1")
                        .param("milestoneName", "마일스톤1")
                        .param("startDate", "2024-01-02")
                        .param("endDate", "2024-01-06"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1/milestone"))
                .andDo(print());
    }

    @Test
    @DisplayName("프로젝트 마일스톤 수정 - 실패 (유효성 검사)")
    void testUpdateMilestone_Fail_Validation() throws Exception {

        mockMvc.perform(put("/project/{projectId}/milestone/update", 1L, 1L)
                        .param("milestoneId", "1")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andDo(print());
    }

}