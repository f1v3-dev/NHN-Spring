package com.nhnacademy.springboot.gateway.domain.task.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {

    private Long roleId;

    private String roleName;
}
