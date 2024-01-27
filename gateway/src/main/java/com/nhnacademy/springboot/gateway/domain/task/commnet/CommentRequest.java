package com.nhnacademy.springboot.gateway.domain.task.commnet;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    @NotBlank
    private String commentText;

    @NotBlank
    private String registrant;
}
