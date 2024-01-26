package com.nhnacademy.springboot.gateway.service;

import com.nhnacademy.springboot.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.ProjectListRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.ProjectRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.TaskDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagResponseDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskAdaptor taskAdaptor;

    public TaskService(TaskAdaptor taskAdaptor) {
        this.taskAdaptor = taskAdaptor;
    }

    public List<TagResponseDto> getTagList(Long projectId) {
        return taskAdaptor.getTagList(projectId);
    }

    public List<Milestone> getMilestoneList() {
        return taskAdaptor.getMilestoneList();
    }

    public List<TaskDto> getTaskList() {
        return taskAdaptor.getTaskList();
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
}
