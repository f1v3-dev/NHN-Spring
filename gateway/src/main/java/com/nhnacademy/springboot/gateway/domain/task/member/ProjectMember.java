package com.nhnacademy.springboot.gateway.domain.task.member;

import com.nhnacademy.springboot.gateway.domain.task.project.Project;
import lombok.Data;

@Data
public class ProjectMember {

    private String userId;

    private Long projectId;

    private Project project;

    private Role role;

}