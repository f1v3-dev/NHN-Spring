package com.nhnacademy.springboot.gateway.domain.task.project;

import com.nhnacademy.springboot.gateway.domain.task.milestone.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListModuleResponse {

    private Project project;

    private Long taskId;

    private String taskName;

    private Milestone milestone;

    private List<TagListModuleResponse> tags;
}
