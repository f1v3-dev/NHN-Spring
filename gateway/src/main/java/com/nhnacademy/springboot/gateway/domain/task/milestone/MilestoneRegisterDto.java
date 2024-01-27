package com.nhnacademy.springboot.gateway.domain.task.milestone;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class MilestoneRegisterDto {

    @Setter
    private Long projectId;

    @NotBlank
    private final String milestoneName;

    private final LocalDate startDate;

    private final LocalDate endDate;


    public MilestoneRegisterDto(Long projectId, String milestoneName, String startDate, String endDate) {
        this.projectId = projectId;
        this.milestoneName = milestoneName;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }
}
