package com.nhnacademy.springboot.gateway.domain.task.commnet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long commentId;

    private String commentText;

    private String registrant;
}
