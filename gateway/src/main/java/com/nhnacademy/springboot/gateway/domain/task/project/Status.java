package com.nhnacademy.springboot.gateway.domain.task.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private Long statusId;

    private String statusName;
}