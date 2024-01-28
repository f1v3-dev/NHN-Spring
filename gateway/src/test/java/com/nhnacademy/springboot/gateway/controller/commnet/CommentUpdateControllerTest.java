package com.nhnacademy.springboot.gateway.controller.commnet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.commnet.CommentRequest;
import com.nhnacademy.springboot.gateway.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CommentUpdateController.class)
class CommentUpdateControllerTest {

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
    @DisplayName("댓글 수정 페이지 요청")
    void testGetCommentUpdateForm() throws Exception {

        mockMvc.perform(get("/task/{taskId}/comment/{commentId}/update", 1L, 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("task/comment/comment-update"))
                .andDo(print());
    }


    @Test
    @DisplayName("댓글 수정 성공")
    void testUpdateComment_Success() throws Exception {

        CommentRequest commentRequest = new CommentRequest("seungjo", "hello");

        mockMvc.perform(put("/task/{taskId}/comment/{commentId}/update", 1L, 1L)
                        .session(session)
                        .param("registrant", commentRequest.getRegistrant())
                        .param("commentText", commentRequest.getCommentText()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/task/1"))
                .andDo(print());
    }
}