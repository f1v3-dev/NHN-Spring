package com.nhnacademy.springboot.gateway.domain.task;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Milestone {

    private Long milestoneId;

    private Long projectId;

    private String milestoneName;

    private LocalDate startDate;

    private LocalDate endDate;
}
