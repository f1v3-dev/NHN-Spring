package com.nhnacademy.springboot.gateway.controller.tag;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.controller.project.tag.TagUpdateController;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TagUpdateController.class)
class TagUpdateControllerTest {

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
    @DisplayName("Tag 수정 페이지 요청")
    void testGetTagUpdateForm() throws Exception {

        mockMvc.perform(get("/project/{projectId}/tag/update", 1L)
                        .session(session)
                        .param("tagId", "1")
                        .param("tagName", "태그 1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("projectId", 1L))
                .andExpect(model().attribute("tag", new TagListModuleResponse(1L, "태그 1")))
                .andExpect(view().name("project/tag/tag-update"));
    }

    @Test
    @DisplayName("Tag 수정 요청")
    void testUpdateTag() throws Exception {

        CreateResponse createResponse = new CreateResponse(1L, "수정되었습니다.");

        given(taskService.updateTag(anyLong(), any()))
                .willReturn(createResponse);

        mockMvc.perform(put("/project/{projectId}/tag/update", 1L)
                        .session(session)
                        .param("tagId", "1")
                        .param("tagName", "태그 1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1/tag"));
    }


}