package com.nhnacademy.springboot.gateway.domain.task.project;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRegisterRequestDto {

    @NotBlank
    private String projectName;

    private String detail;

    private String userId;
}
