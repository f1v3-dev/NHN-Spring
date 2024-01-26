package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.domain.task.CreateResponse;
import com.nhnacademy.springboot.gateway.domain.task.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.ProjectRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.ProjectListRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagResponseDto;
import com.nhnacademy.springboot.gateway.domain.task.TaskDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagRequestDto;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagDto;
import java.util.List;

public interface TaskAdaptor {

    List<TagResponseDto> getTagList(Long projectId);

    List<Milestone> getMilestoneList();

    List<TaskDto> getTaskList();

    List<ProjectListRequestDto> getProjectList(String userId);

    CreateResponse registerProject(ProjectRegisterRequestDto project);

    CreateResponse registerTag(TagRequestDto tagRegisterDto);

    void deleteTag(Long tagId);

    TagDto getTag(Long tagId);

    CreateResponse updateTag(Long projectId, TagRequestDto tagUpdateRequestDto);
}
