package com.nhnacademy.springboot.gateway.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {

    /**
     * 1 : 활성
     * 2 : 휴면
     * 3 : 종료
     */

    private Long statusId;
}
