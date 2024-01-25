package com.nhnacademy.springboot.gateway.domain.task;

import java.util.List;
import lombok.Data;

@Data
public class TaskRequestDto {

    private List<Milestone> milestoneList;

    private List<Tag> tagList;
}
