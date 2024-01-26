package com.nhnacademy.springboot.gateway.domain.task;

import com.nhnacademy.springboot.gateway.domain.task.tag.TagDto;
import java.util.List;
import lombok.Data;

@Data
public class TaskRequestDto {

    private List<Milestone> milestoneList;

    private List<TagDto> tagList;
}
