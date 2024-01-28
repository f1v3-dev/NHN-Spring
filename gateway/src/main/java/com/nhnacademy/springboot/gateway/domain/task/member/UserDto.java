package com.nhnacademy.springboot.gateway.domain.task.member;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String userId;
}
