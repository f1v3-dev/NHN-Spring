package com.nhnacademy.springboot.gateway.domain.task.member;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberListResponse {

    private String UserId;

    private Role role;
}