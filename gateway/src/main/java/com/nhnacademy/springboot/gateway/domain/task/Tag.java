package com.nhnacademy.springboot.gateway.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tag {

    private Long tagId;

    private String tagName;

    private Long projectId;
}
