package com.nhnacademy.springboot.gateway.domain.task.task;

import com.nhnacademy.springboot.gateway.domain.task.project.ProjectDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListResponse {

    private ProjectDto project;

    private List<TaskListModule> task;
}
