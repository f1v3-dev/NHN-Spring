package com.nhnacademy.springboot.gateway.controller.project.member;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.CheckAccount;
import com.nhnacademy.springboot.gateway.domain.task.member.UserDto;
import com.nhnacademy.springboot.gateway.service.AccountService;
import com.nhnacademy.springboot.gateway.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberRegisterController.class)
class MemberRegisterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    @MockBean
    AccountService accountService;

    MockHttpSession session = new MockHttpSession();

    @BeforeEach
    void setup() {
        Account account = new Account(1L, "정승조", "seungjo", "1234",
                "seungjo@nhn.com", "010-1234-1234", "JOINED");

        session.setAttribute("account", account);
    }

    @Test
    @DisplayName("프로젝트 멤버 등록 폼")
    void testGetMemberRegisterForm() throws Exception {

        mockMvc.perform(get("/project/{projectId}/member/register", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("project/member/member-register"))
                .andExpect(model().attributeExists("projectId"))
                .andDo(print());
    }

    @Test
    @DisplayName("프로젝트 멤버 등록 - 성공")
    void testRegisterMember_Success() throws Exception {

        UserDto user = new UserDto("seungjo");
        CheckAccount isTrue = new CheckAccount(true);
        given(accountService.isAccountExist(user)).willReturn(isTrue);

        mockMvc.perform(post("/project/{projectId}/member/register", 1L)
                        .session(session)
                        .param("userId", user.getUserId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1/member"))
                .andDo(print());
    }

    @Test
    @DisplayName("프로젝트 멤버 등록 - 실패(존재하지 않는 회원)")
    void testRegisterMember_Fail() throws Exception {

        UserDto user = new UserDto("seungjo");
        CheckAccount isFail = new CheckAccount(false);
        given(accountService.isAccountExist(user)).willReturn(isFail);

        mockMvc.perform(post("/project/{projectId}/member/register", 1L)
                        .session(session)
                        .param("userId", user.getUserId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/project/1/member/register"))
                .andDo(print());
    }

    @Test
    @DisplayName("프로젝트 멤버 등록 - 실패(유효성 검사)")
    void testRegisterMember_Fail_Validation() throws Exception {

        mockMvc.perform(post("/project/{projectId}/member/register", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andDo(print());
    }

}