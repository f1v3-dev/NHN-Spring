package com.nhnacademy.springboot.gateway.controller.project.milestone;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MilestoneRegisterController.class)
class MilestoneRegisterControllerTest {

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
    @DisplayName("프로젝트 마일스톤 등록 폼 요청")
    void testGetMilestoneRegisterForm() throws Exception {

        mockMvc.perform(get("/project/{projectId}/milestone/register", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("project/milestone/milestone-register"))
                .andExpect(model().attributeExists("projectId"))
                .andDo(print());
    }

    @Test
    @DisplayName("프로젝트 마일스톤 등록 - 성공")
    void testRegisterMilestone_Success() throws Exception {

        mockMvc.perform(post("/project/{projectId}/milestone/register", 1L)
                        .session(session)
                        .param("projectId", "1")
                        .param("milestoneName", "마일스톤1")
                        .param("startDate", "2024-01-02")
                        .param("endDate", "2024-01-06"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1/milestone"))
                .andDo(print());
    }

    @Test
    @DisplayName("프로젝트 마일스톤 등록 - 실패 (유효성 검사)")
    void testRegisterMilestone_Fail_Validation() throws Exception {

        mockMvc.perform(post("/project/{projectId}/milestone/register", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andDo(print());
    }
}