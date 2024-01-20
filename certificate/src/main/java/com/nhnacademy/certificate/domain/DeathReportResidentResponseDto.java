package com.nhnacademy.certificate.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeathReportResidentResponseDto {

    private String name;

    private String residentRegistrationNumber;

    private String qualificationCode;

    private String email;

    private String phoneNumber;

    private LocalDate birthDeathReportDate;


}
