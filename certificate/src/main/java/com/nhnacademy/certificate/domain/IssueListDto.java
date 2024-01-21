package com.nhnacademy.certificate.domain;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IssueListDto {

    private Long certificateConfirmationNumber;

    private String certificateTypeCode;

    private LocalDate certificateIssueDate;

}
