package com.nhnacademy.springboot.gateway.domain.task.milestone;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MilestoneDto {
    private Long milestoneId;

    private String milestoneName;
    private Date startDate;
    private Date endDate;

    public Milestone toEntity() {
        return new Milestone(milestoneId, milestoneName, startDate, endDate);

    }
}
