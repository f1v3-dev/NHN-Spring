package com.nhnacademy.springboot.gateway.domain.task.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Project {

    private Long projectId;

    private String projectName;

    private String detail;

    private Status status;
}