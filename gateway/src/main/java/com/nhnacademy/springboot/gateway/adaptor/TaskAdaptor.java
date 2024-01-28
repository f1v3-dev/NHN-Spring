package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponseString;
import com.nhnacademy.springboot.gateway.domain.task.StatusDto;
import com.nhnacademy.springboot.gateway.domain.task.commnet.CommentRequest;
import com.nhnacademy.springboot.gateway.domain.task.member.ProjectMemberListResponse;
import com.nhnacademy.springboot.gateway.domain.task.member.UserDto;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneRegisterDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectListRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskListResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskRegisterDto;
import java.util.List;

public interface TaskAdaptor {

    List<TagListModuleResponse> getTagList(Long projectId);

    List<MilestoneDto> getMilestoneList(Long projectId);

    TaskListResponse getTaskList(Long projectId);

    List<ProjectListRequestDto> getProjectList(String userId);

    CreateResponse registerProject(ProjectRegisterRequestDto project);

    CreateResponse registerTag(TagRequestDto tagRegisterDto);

    CreateResponse deleteTag(Long projectId, Long tagId);

    TagDto getTag(Long tagId);

    CreateResponse updateTag(Long projectId, TagRequestDto tagUpdateRequestDto);

    CreateResponse registerMilestone(MilestoneRegisterDto milestone);

    MilestoneDto getMilestone(Long milestoneId);


    CreateResponse updateMilestone(Long milestoneId, MilestoneRegisterDto registerDto);

    CreateResponse registerTask(TaskRegisterDto task);

    TaskModuleResponse getTask(Long taskId);

    CreateResponse registerComment(Long taskId, CommentRequest comment);

    CreateResponse editProjectStatus(Long projectId, StatusDto statusDto);

    List<ProjectMemberListResponse> getMemberListByProjectId(Long projectId);

    CreateResponseString registerMember(Long projectId, UserDto user);

    CreateResponseString deleteUserFromProject(Long projectId, UserDto user);

    CreateResponse deleteComment(Long taskId, Long commentId);

    CreateResponse updateComment(Long taskId, Long commentId, CommentRequest commentRequest);
}
