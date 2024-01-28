package com.nhnacademy.springboot.gateway.controller.commnet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.commnet.CommentRequest;
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

@WebMvcTest(CommentController.class)
class CommentControllerTest {

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
    @DisplayName("댓글 등록하기")
    void testRegisterComment() throws Exception {

        CommentRequest commentRequest = new CommentRequest("nhn-admin", "comment-text");
        mockMvc.perform(post("/task/{taskId}/comment/register", 1L)
                        .session(session)
                        .param("registrant", commentRequest.getRegistrant())
                        .param("commentText", commentRequest.getCommentText()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/task/1"))
                .andDo(print());
    }

    @Test
    @DisplayName("댓글 등록 실패 - 유효성 검사")
    void testRegisterComment_Fail() throws Exception {


        MvcResult mvcResult = mockMvc.perform(post("/task/{taskId}/comment/register", 1L)
                        .session(session))
                .andExpect(content().contentType(MediaType.TEXT_HTML + ";charset=UTF-8"))
                .andDo(print())
                .andReturn();

        String viewName = mvcResult.getModelAndView().getViewName();

        Assertions.assertThat(viewName).isEqualTo("error");

    }
}