package com.nhnacademy.springboot.gateway.service;

import com.nhnacademy.springboot.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.TaskUser;
import com.nhnacademy.springboot.gateway.domain.task.commnet.CommentRequest;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneDto;
import com.nhnacademy.springboot.gateway.domain.task.milestone.MilestoneRegisterDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectListRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.project.ProjectRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskListResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskModuleResponse;
import com.nhnacademy.springboot.gateway.domain.task.task.TaskRegisterDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskAdaptor taskAdaptor;

    public TaskService(TaskAdaptor taskAdaptor) {
        this.taskAdaptor = taskAdaptor;
    }

    public List<TagListModuleResponse> getTagList(Long projectId) {
        return taskAdaptor.getTagList(projectId);
    }

    public List<MilestoneDto> getMilestoneList(Long projectId) {
        return taskAdaptor.getMilestoneList(projectId);
    }

    public TaskListResponse getTaskList(Long projectId) {
        return taskAdaptor.getTaskList(projectId);
    }

    public List<ProjectListRequestDto> getProjectList(String userId) {
        return taskAdaptor.getProjectList(userId);
    }

    public CreateResponse registerProject(ProjectRegisterRequestDto project) {
        return taskAdaptor.registerProject(project);
    }

    public CreateResponse registerTag(TagRequestDto tagRegisterDto) {
        return taskAdaptor.registerTag(tagRegisterDto);
    }

    public void deleteTag(Long tagId) {
        taskAdaptor.deleteTag(tagId);
    }

    public CreateResponse updateTag(Long projectId, TagRequestDto tagUpdateRequestDto) {
        return taskAdaptor.updateTag(projectId, tagUpdateRequestDto);
    }

    public TaskUser matches(String userId) {
        return taskAdaptor.matches(userId);
    }

    public CreateResponse registerMilestone(MilestoneRegisterDto milestone) {
        return taskAdaptor.registerMilestone(milestone);
    }

    public MilestoneDto getMilestone(Long milestoneId) {
        return taskAdaptor.getMilestone(milestoneId);
    }



    public CreateResponse updateMilestone(Long milestoneId, MilestoneRegisterDto registerDto) {
        return taskAdaptor.updateMilestone(milestoneId, registerDto);
    }

    public CreateResponse registerTask(TaskRegisterDto task) {
        return taskAdaptor.registerTask(task);
    }

    public TaskModuleResponse getTask(Long taskId) {
        return taskAdaptor.getTask(taskId);
    }

    public CreateResponse registerComment(Long taskId, CommentRequest comment) {
        return taskAdaptor.registerComment(taskId, comment);
    }
}
