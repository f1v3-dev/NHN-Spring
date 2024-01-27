package com.nhnacademy.springboot.gateway.domain.task.milestone;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Setter;

@Data
public class MilestoneUpdateDto {

    @Setter
    private Long projectId;

    private Long milestoneId;

    @NotBlank
    private String milestoneName;

    private LocalDate startDate;

    private LocalDate endDate;

    public MilestoneRegisterDto toRegisterDto() {
        return new MilestoneRegisterDto(projectId, milestoneName, startDate.toString(), endDate.toString());
    }
}
