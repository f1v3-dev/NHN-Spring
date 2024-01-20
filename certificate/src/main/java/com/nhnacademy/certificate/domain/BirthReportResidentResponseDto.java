package com.nhnacademy.certificate.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class BirthReportResidentResponseDto {

    private String email;

    private String phoneNumber;

    private String qualificationCode;

    private String name;

    private String residentRegistrationNumber;

    private LocalDate birthDeathReportDate;
}
