package com.nhnacademy.springboot.gateway.domain.task.project;

import lombok.Data;

@Data
public class ProjectRegisterRequestDto {

    private String projectName;

    private String detail;

    private String userId;
}
