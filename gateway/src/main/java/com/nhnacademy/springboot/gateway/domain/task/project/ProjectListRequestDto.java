package com.nhnacademy.springboot.gateway.domain.task.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectListRequestDto {

    /**
     * 프로젝트 ID, 프로젝트명, 상태
     *
     *
     *     private Long projectId;
     *     private String projectName;
     *     private String statusName;
     */

    private Long projectId;

    private String projectName;

    private String statusName;
}
