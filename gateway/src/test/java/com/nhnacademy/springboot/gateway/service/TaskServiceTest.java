package com.nhnacademy.springboot.gateway.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nhnacademy.springboot.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponseString;
import com.nhnacademy.springboot.gateway.domain.task.StatusDto;
import com.nhnacademy.springboot.gateway.domain.task.commnet.CommentRequest;
import com.nhnacademy.springboot.gateway.domain.task.member.ProjectMemberListResponse;
import com.nhnacademy.springboot.gateway.domain.task.member.Role;
import com.nhnacademy.springboot.gateway.domain.task.member.UserDto;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneRegisterDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectListRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.project.Status;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskListModule;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskListResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskRegisterDto;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    TaskService taskService;

    @MockBean
    TaskAdaptor taskAdaptor;

    @Test
    @DisplayName("1번 프로젝트의 태그 리스트 가져오기")
    void testGetTagList() {
        given(taskAdaptor.getTagList(anyLong())).willReturn(
                List.of(new TagListModuleResponse(1L, "tag#1"), new TagListModuleResponse(2L, "tag#2"),
                        new TagListModuleResponse(3L, "tag#3")));

        assertThat(taskService.getTagList(1L)).hasSize(3);
    }

    @Test
    @DisplayName("1번 프로젝트의 마일스톤 리스트 가져오기")
    void testGetMilestoneList() {
        given(taskAdaptor.getMilestoneList(1L)).willReturn(
                List.of(new MilestoneDto(1L, "milestone#1", Date.valueOf("2020-02-02"), Date.valueOf("2020-02-02")),
                        new MilestoneDto(2L, "milestone#2", Date.valueOf("2020-02-02"), Date.valueOf("2020-02-02")),
                        new MilestoneDto(3L, "milestone#3", Date.valueOf("2020-02-02"), Date.valueOf("2020-02-02"))));

        assertThat(taskService.getMilestoneList(1L)).hasSize(3);
    }

    @Test
    @DisplayName("Task 리스트 가져오기")
    void testGetTaskList() {

        ProjectDto project = new ProjectDto(1L, "project#1", "프로젝트 #1", new Status(1L, "완료"));

        TaskListResponse taskListResponse =
                new TaskListResponse(project, List.of(new TaskListModule(1L, "task", null, null)));

        given(taskAdaptor.getTaskList(anyLong())).willReturn(taskListResponse);

        TaskListResponse taskList = taskService.getTaskList(1L);
        assertThat(taskList).isNotNull();
        assertThat(taskList.getTask()).hasSize(1);
    }

    @Test
    @DisplayName("사용자 아이디로 Project 리스트 가져오기")
    void testGetProjectList() {

        given(taskAdaptor.getProjectList(anyString())).willReturn(
                List.of(new ProjectListRequestDto(1L, "nhn", "ing"), new ProjectListRequestDto(2L, "play", "end"),
                        new ProjectListRequestDto(3L, "music", "ing")));

        Assertions.assertThat(taskService.getProjectList("nhn")).hasSize(3);
    }


    @Test
    @DisplayName("프로젝트 등록하기")
    void testRegisterProject() {
        given(taskAdaptor.registerProject(any())).willReturn(new CreateResponse(1L, "정상적으로 완료 되었습니다"));

        Assertions.assertThat(taskService.registerProject(new ProjectRegisterRequestDto()).getMessage())
                .isEqualTo("정상적으로 완료 되었습니다");
    }

    @Test
    @DisplayName("태그 등록하기")
    void testRegisterTag() {
        given(taskAdaptor.registerTag(any())).willReturn(new CreateResponse(1L, "정상적으로 완료 되었습니다"));

        assertThat(taskService.registerTag(new TagRequestDto(1L, "tag#1")).getMessage()).isEqualTo("정상적으로 완료 되었습니다");
    }

    @Test
    @DisplayName("태그 삭제하기")
    void testDeleteTag() {
        taskService.deleteTag(1L, 1L);

        verify(taskAdaptor, times(1)).deleteTag(1L, 1L);
    }

    @Test
    @DisplayName("태그 수정하기")
    void testUpdateTag() {
        given(taskAdaptor.updateTag(anyLong(), any())).willReturn(new CreateResponse(1L, "정상적으로 완료 되었습니다"));

        assertThat(taskService.updateTag(1L, new TagRequestDto(1L, "tag#1")).getMessage()).isEqualTo("정상적으로 완료 되었습니다");
    }

    @Test
    @DisplayName("마일스톤 등록")
    void testRegisterMilestone() {
        given(taskAdaptor.registerMilestone(any())).willReturn(new CreateResponse(1L, "정상적으로 완료 되었습니다"));


        MilestoneRegisterDto milestone =
                new MilestoneRegisterDto(1L, "milestone#1", LocalDate.now().toString(), LocalDate.now().toString());

        CreateResponse response = taskService.registerMilestone(milestone);

        assertThat(response.getMessage()).isEqualTo("정상적으로 완료 되었습니다");
    }

    @Test
    @DisplayName("마일스톤 조회")
    void testGetMilestone() {
        given(taskAdaptor.getMilestone(anyLong())).willReturn(
                new MilestoneDto(1L, "milestone#1", Date.valueOf("2020-02-02"), Date.valueOf("2020-02-02")));

        MilestoneDto milestone = taskService.getMilestone(1L);

        assertThat(milestone.getMilestoneId()).isEqualTo(1L);
        assertThat(milestone.getMilestoneName()).isEqualTo("milestone#1");
    }

    @Test
    @DisplayName("마일스톤 수정")
    void testUpdateMilestone() {
        given(taskAdaptor.updateMilestone(anyLong(), any())).willReturn(new CreateResponse(1L, "정상적으로 완료 되었습니다"));

        MilestoneRegisterDto milestone =
                new MilestoneRegisterDto(1L, "milestone#1", LocalDate.now().toString(), LocalDate.now().toString());

        CreateResponse response = taskService.updateMilestone(1L, milestone);

        assertThat(response.getMessage()).isEqualTo("정상적으로 완료 되었습니다");
    }

    @Test
    @DisplayName("업무 삭제")
    void testDeleteTask() {
        given(taskAdaptor.deleteTask(anyLong())).willReturn(new CreateResponse(1L, "정상적으로 삭제 되었습니다."));

        CreateResponse response = taskService.deleteTask(1L);

        assertThat(response.getMessage()).isEqualTo("정상적으로 삭제 되었습니다.");
    }

    @Test
    @DisplayName("업무 수정")
    void testUpdateTask() {
        given(taskAdaptor.updateTask(anyLong(), any())).willReturn(new CreateResponse(1L, "정상적으로 수정 되었습니다."));

        CreateResponse response = taskService.updateTask(1L, new TaskRegisterDto());

        assertThat(response.getMessage()).isEqualTo("정상적으로 수정 되었습니다.");
    }

    @Test
    @DisplayName("업무 등록")
    void testRegisterTask() {
        given(taskAdaptor.registerTask(any())).willReturn(new CreateResponse(1L, "정상적으로 등록 되었습니다."));

        CreateResponse response = taskService.registerTask(new TaskRegisterDto());

        assertThat(response.getMessage()).isEqualTo("정상적으로 등록 되었습니다.");
    }

    @Test
    @DisplayName("업무 조회")
    void testGetTask() {
        given(taskAdaptor.getTask(anyLong())).willReturn(
                new TaskModuleResponse(1L, "task", "task description", "seungjo", null, null, null));

        TaskModuleResponse response = taskService.getTask(1L);

        assertThat(response.getTaskId()).isEqualTo(1L);
        assertThat(response.getTaskName()).isEqualTo("task");
        assertThat(response.getRegistrant()).isEqualTo("seungjo");
    }

    @Test
    @DisplayName("댓글 등록")
    void testRegisterComment() {
        given(taskAdaptor.registerComment(anyLong(), any())).willReturn(new CreateResponse(1L, "정상적으로 등록 되었습니다."));

        CreateResponse response = taskService.registerComment(1L, new CommentRequest("seungjo", "hihi"));


        assertThat(response.getMessage()).isEqualTo("정상적으로 등록 되었습니다.");
    }

    @Test
    @DisplayName("프로젝트 상태 변경")
    void testEditProjectStatus() {
        given(taskAdaptor.editProjectStatus(anyLong(), any())).willReturn(new CreateResponse(1L, "정상적으로 변경 되었습니다."));

        CreateResponse response = taskService.editProjectStatus(1L, new StatusDto(2L));

        assertThat(response.getMessage()).isEqualTo("정상적으로 변경 되었습니다.");
    }

    @Test
    @DisplayName("프로젝트 멤버 리스트 조회")
    void testGetMemberListByProjectId() {

        List<ProjectMemberListResponse> memberList =
                List.of(new ProjectMemberListResponse("seungjo", new Role(1L, "관리자")),
                        new ProjectMemberListResponse("f1v3", new Role(2L, "참여자")));

        given(taskAdaptor.getMemberListByProjectId(anyLong())).willReturn(memberList);

        List<ProjectMemberListResponse> memberListByProjectId = taskService.getMemberListByProjectId(1L);

        assertThat(memberListByProjectId).hasSize(2);
        assertThat(memberListByProjectId.get(0).getUserId()).isEqualTo("seungjo");
        assertThat(memberListByProjectId.get(1).getUserId()).isEqualTo("f1v3");
    }

    @Test
    @DisplayName("프로젝트 멤버 등록")
    void testRegisterMember() {
        given(taskAdaptor.registerMember(anyLong(), any()))
                .willReturn(new CreateResponseString("seungjo", "정상적으로 등록 되었습니다."));

        CreateResponseString response = taskService.registerMember(1L, new UserDto("seungjo"));

        assertThat(response.getMessage()).isEqualTo("정상적으로 등록 되었습니다.");
    }

    @Test
    @DisplayName("프로젝트 멤버 삭제")
    void testDeleteMemberFromProject() {
        given(taskAdaptor.deleteUserFromProject(anyLong(), any()))
                .willReturn(new CreateResponseString("seungjo", "정상적으로 삭제 되었습니다."));

        CreateResponseString response = taskService.deleteMemberFromProject(1L, new UserDto("seungjo"));

        assertThat(response.getMessage()).isEqualTo("정상적으로 삭제 되었습니다.");
    }

    @Test
    @DisplayName("댓글 삭제")
    void testDeleteComment() {
        given(taskAdaptor.deleteComment(anyLong(), anyLong()))
                .willReturn(new CreateResponse(1L, "정상적으로 삭제 되었습니다."));

        CreateResponse response = taskService.deleteComment(1L, 1L);

        assertThat(response.getMessage()).isEqualTo("정상적으로 삭제 되었습니다.");
    }

    @Test
    @DisplayName("댓글 수정")
    void testUpdateComment() {
        given(taskAdaptor.updateComment(anyLong(), anyLong(), any()))
                .willReturn(new CreateResponse(1L, "정상적으로 수정 되었습니다."));

        CreateResponse response = taskService.updateComment(1L, 1L, new CommentRequest("seungjo", "hihi"));

        assertThat(response.getMessage()).isEqualTo("정상적으로 수정 되었습니다.");
    }
}