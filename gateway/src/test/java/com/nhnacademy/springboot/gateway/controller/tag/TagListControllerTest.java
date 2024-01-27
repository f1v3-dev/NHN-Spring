package com.nhnacademy.springboot.gateway.controller.tag;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.springboot.gateway.controller.project.tag.TagListController;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TagListController.class)
class TagListControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    @Test
    @DisplayName("Tag 목록 페이지 요청")
    void testGetTagList() throws Exception {
        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("account", account);

        List<TagListModuleResponse> tagList = List.of(new TagListModuleResponse(1L, "Controller"),
                new TagListModuleResponse(2L, "View"));

        given(taskService.getTagList(anyLong())).willReturn(tagList);

        mockMvc.perform(get("/project/{projectId}/tag", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("tagList"))
                .andExpect(model().attribute("tagList", tagList))
                .andDo(print());
    }

}