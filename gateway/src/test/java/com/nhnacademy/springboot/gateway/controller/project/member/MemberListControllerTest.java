package com.nhnacademy.springboot.gateway.controller.project.member;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.member.ProjectMemberListResponse;
import com.nhnacademy.springboot.gateway.domain.task.member.Role;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberListController.class)
class MemberListControllerTest {

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
    @DisplayName("프로젝트 멤버 목록 조회")
    void testGetProjectMemberList() throws Exception {

        List<ProjectMemberListResponse> memberList
                = List.of(
                new ProjectMemberListResponse("seungjo", new Role(1L, "관리자")),
                new ProjectMemberListResponse("f1v3", new Role(2L, "멤버"))
        );

        given(taskService.getMemberListByProjectId(anyLong())).willReturn(memberList);

        mockMvc.perform(get("/project/{projectId}/member", 1L)
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("project/member/member-list"))
                .andDo(print());

    }

}