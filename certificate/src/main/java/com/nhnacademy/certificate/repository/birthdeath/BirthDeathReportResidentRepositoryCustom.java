package com.nhnacademy.certificate.repository.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.domain.DeathReportResidentResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BirthDeathReportResidentRepositoryCustom {

    BirthReportResidentResponseDto findBirthReportResident(Integer residentSerialNumber);

    DeathReportResidentResponseDto findDeathReportResident(Integer residentSerialNumber);
}
