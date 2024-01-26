package com.nhnacademy.springboot.gateway.domain.task.tag;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagDto {

    private Long tagId;

    private String tagName;

    private Long projectId;
}
