package com.nhnacademy.springboot.gateway.domain.task.task;

import com.nhnacademy.springboot.gateway.domain.task.commnet.CommentDTO;
import com.nhnacademy.springboot.gateway.domain.task.milestone.Milestone;
import com.nhnacademy.springboot.gateway.domain.task.tag.TagListModuleResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskModuleResponse {
    private Long taskId;

    private String taskName;

    private String taskDetails;

    private String registrant;

    private List<CommentDTO> comments;

    private Milestone mileStone;

    private List<TagListModuleResponse> tags;
}
