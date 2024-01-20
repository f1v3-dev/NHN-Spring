package com.nhnacademy.certificate.service.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.domain.DeathReportResidentResponseDto;

public interface BirthDeathReportResidentService {
    BirthReportResidentResponseDto findBirthReportResident(Integer residentSerialNumber);

    DeathReportResidentResponseDto findDeathReportResident(Integer residentSerialNumber);
}
