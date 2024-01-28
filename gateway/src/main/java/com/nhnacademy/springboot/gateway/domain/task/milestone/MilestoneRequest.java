package com.nhnacademy.springboot.gateway.domain.task.milestone;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MilestoneRequest {

    private Long projectId;

    private String milestoneName;
    private LocalDate startDate;
    private LocalDate endDate;
}
