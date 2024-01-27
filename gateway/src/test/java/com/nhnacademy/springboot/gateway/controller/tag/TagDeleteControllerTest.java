package com.nhnacademy.springboot.gateway.controller.tag;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.springboot.gateway.controller.project.tag.TagDeleteController;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TagDeleteController.class)
class TagDeleteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    @Test
    @DisplayName("Tag 삭제 요청")
    void testDeleteTag() throws Exception {

        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("account", account);

        mockMvc.perform(delete("/project/{projectId}/tag/delete", 1L)
                        .session(session)
                        .param("tagId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1/tag"));
    }

}