package com.nhnacademy.springboot.gateway.controller.tag;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.controller.project.tag.TagRegisterController;
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

@WebMvcTest(TagRegisterController.class)
class TagRegisterControllerTest {

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
    @DisplayName("Tag 등록 페이지 요청")
    void testGetTagRegisterForm() throws Exception {

        mockMvc.perform(get("/project/{projectId}/tag/register", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("projectId"))
                .andExpect(model().attribute("projectId", 1L))
                .andExpect(view().name("project/tag/tag-register"));
    }

    @Test
    @DisplayName("Tag 등록 요청")
    void testRegisterTag() throws Exception {

        mockMvc.perform(post("/project/{projectId}/tag/register", 1L)
                        .session(session)
                        .param("tagName", "태그 1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project"))
                .andDo(print());
    }

}