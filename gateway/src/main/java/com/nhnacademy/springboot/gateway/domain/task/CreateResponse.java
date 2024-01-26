package com.nhnacademy.springboot.gateway.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateResponse {
    private Long id;
    private String message;
}
