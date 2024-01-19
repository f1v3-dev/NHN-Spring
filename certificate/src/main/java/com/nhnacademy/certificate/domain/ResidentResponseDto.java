package com.nhnacademy.certificate.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResidentResponseDto {

    private String relationWithHeadOfHousehold;

    private String name;

    private String registrationNumber;

    private LocalDate reportDate;

    private String changeReason;
}
