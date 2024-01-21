package com.nhnacademy.certificate.service.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.domain.DeathReportResidentResponseDto;
import com.nhnacademy.certificate.domain.rest.BirthRequestDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;

public interface BirthDeathReportResidentService {
    BirthReportResidentResponseDto findBirthReportResident(Integer residentSerialNumber);

    DeathReportResidentResponseDto findDeathReportResident(Integer residentSerialNumber);

    BirthDeathReportResident registerBirth(Integer reportSerialNumber, BirthRequestDto birthReport);

    BirthDeathReportResident modify(Integer reportSerialNumber, BirthRequestDto birthReport);

    void deleteBirthReport(Integer reportSerialNumber, Integer targetSerialNumber);


}
