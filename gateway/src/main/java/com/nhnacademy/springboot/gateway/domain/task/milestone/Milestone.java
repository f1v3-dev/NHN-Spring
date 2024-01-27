package com.nhnacademy.springboot.gateway.domain.task.milestone;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Milestone {
    private Long milestoneId;
    private String milestoneName;
    private Date startDate;
    private Date endDate;
}
