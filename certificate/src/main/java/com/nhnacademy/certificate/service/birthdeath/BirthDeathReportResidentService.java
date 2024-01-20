package com.nhnacademy.certificate.service.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;

public interface BirthDeathReportResidentService {
    BirthReportResidentResponseDto findReportResident(Integer residentSerialNumber);
}
