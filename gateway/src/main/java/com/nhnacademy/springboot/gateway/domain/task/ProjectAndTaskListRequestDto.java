package com.nhnacademy.springboot.gateway.domain.task;

import java.util.List;
import lombok.Data;

@Data
public class ProjectAndTaskListRequestDto {

    private ProjectDto project;

    private List<TaskDto> taskList;
}
