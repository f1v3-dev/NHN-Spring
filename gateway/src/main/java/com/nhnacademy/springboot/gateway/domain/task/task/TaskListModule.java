package com.nhnacademy.springboot.gateway.domain.task.task;

import com.nhnacademy.springboot.gateway.domain.task.milestone.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListModule {
    private Long taskId;

    private String taskName;

    private Milestone milestoneDTO;

    private List<TagListModuleResponse> tags;
}
