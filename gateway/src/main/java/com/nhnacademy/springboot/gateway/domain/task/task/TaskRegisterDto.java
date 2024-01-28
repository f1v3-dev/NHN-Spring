package com.nhnacademy.springboot.gateway.domain.task.task;


import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class TaskRegisterDto {

    @NotBlank
    private String taskName;

    @NotBlank
    private String taskDetails;

    @NotBlank
    private String registrant;

    private List<Long> tagIds;

    private Long milestoneId;

    private Long projectId;

}
