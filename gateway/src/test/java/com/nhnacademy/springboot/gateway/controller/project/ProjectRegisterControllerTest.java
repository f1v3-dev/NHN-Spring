package com.nhnacademy.springboot.gateway.controller.project;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectRegisterRequestDto;
import com.nhnacademy.springboot.gateway.service.TaskService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(ProjectRegisterController.class)
class ProjectRegisterControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    @Autowired
    ObjectMapper objectMapper;

    MockHttpSession session = new MockHttpSession();

    @BeforeEach
    void setUp() {
        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");


        session.setAttribute("account", account);
    }

    @Test
    @DisplayName("Project 등록 페이지 요청")
    void testGetProjectRegisterForm() throws Exception {

        mockMvc.perform(get("/project/register")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attribute("userId", "seungjo"))
                .andExpect(view().name("project/register"));
    }

    @Test
    @DisplayName("Project 등록 하기")
    void testRegisterProject() throws Exception {

        ProjectRegisterRequestDto project =
                new ProjectRegisterRequestDto("nhn", "detail", "seungjo");

        given(taskService.registerProject(any(ProjectRegisterRequestDto.class))).willReturn(new CreateResponse());

        mockMvc.perform(post("/project/register")
                        .session(session)
                        .param("projectName", project.getProjectName())
                        .param("detail", project.getDetail())
                        .param("userId", project.getUserId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("Project 등록 (유효성 검사 실패)")
    void testRegisterFailProject() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/project/register")
                        .session(session))
                .andDo(print())
                .andReturn();

        String viewName = mvcResult.getModelAndView().getViewName();

        Assertions.assertThat(viewName).isEqualTo("error");
    }


}