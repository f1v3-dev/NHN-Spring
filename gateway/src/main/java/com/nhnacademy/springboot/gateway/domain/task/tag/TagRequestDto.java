package com.nhnacademy.springboot.gateway.domain.task.tag;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TagRequestDto {

    private Long projectId;

    private String tagName;

}
