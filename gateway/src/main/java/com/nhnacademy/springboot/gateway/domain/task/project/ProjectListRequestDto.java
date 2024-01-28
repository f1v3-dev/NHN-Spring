package com.nhnacademy.springboot.gateway.domain.task.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectListRequestDto {

    private Long projectId;

    private String projectName;

    private String statusName;
}
