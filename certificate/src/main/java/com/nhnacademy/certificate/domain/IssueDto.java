package com.nhnacademy.certificate.domain;

import java.time.LocalDate;

public interface IssueDto {

    Long getCertificateConfirmationNumber();

    LocalDate getCertificateIssueDate();
}
