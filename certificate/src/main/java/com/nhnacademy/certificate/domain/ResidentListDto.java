package com.nhnacademy.certificate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResidentListDto {

    private Integer residentSerialNumber;

    private String name;

    private Integer householdSerialNumber;

    private String birthDeathTypeCode;

    private Integer certificateNumber;
}
