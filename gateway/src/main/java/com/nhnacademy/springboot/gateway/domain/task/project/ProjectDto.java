package com.nhnacademy.springboot.gateway.domain.task.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDto {

    private Long projectId;

    private String projectName;

    private String detail;

    private Status status;
}
