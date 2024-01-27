package com.nhnacademy.springboot.gateway.domain.task.project;

import lombok.Data;

@Data
public class ProjectDto {

    private Long projectId;

    private String projectName;
    private String detail;
    private Status status;
}
