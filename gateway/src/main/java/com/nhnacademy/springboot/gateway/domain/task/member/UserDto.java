package com.nhnacademy.springboot.gateway.domain.task.member;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    private String userId;
}
