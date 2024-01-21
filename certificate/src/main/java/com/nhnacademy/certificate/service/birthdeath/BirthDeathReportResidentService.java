package com.nhnacademy.certificate.service.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.domain.DeathReportResidentResponseDto;
import com.nhnacademy.certificate.domain.rest.BirthDeathRequestDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;

public interface BirthDeathReportResidentService {
    BirthReportResidentResponseDto findBirthReportResident(Integer residentSerialNumber);

    DeathReportResidentResponseDto findDeathReportResident(Integer residentSerialNumber);

    BirthDeathReportResident register(Integer reportSerialNumber, BirthDeathRequestDto birthReport,
                                      String birthDeathTypeCode);

    BirthDeathReportResident modify(Integer reportSerialNumber, BirthDeathRequestDto birthReport,
                                    String birthDeathTypeCode);


    void delete(Integer reportSerialNumber, Integer targetSerialNumber, String birthDeathTypeCode);


}
