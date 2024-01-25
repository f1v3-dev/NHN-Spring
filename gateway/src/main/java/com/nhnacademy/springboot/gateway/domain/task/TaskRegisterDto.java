package com.nhnacademy.springboot.gateway.domain.task;


import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class TaskRegisterDto {

    private List<Long> tagIdList;

    private Long milestoneId;

    @NotBlank
    private String taskName;

    @NotBlank
    private String taskDetails;

    @NotBlank
    private String registrant;


}
