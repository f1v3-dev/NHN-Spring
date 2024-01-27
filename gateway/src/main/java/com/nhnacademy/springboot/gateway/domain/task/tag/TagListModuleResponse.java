package com.nhnacademy.springboot.gateway.domain.task.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListModuleResponse {
    private Long tagId;
    private String tagName;
}
