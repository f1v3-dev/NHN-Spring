package com.nhnacademy.certificate.repository.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BirthDeathReportResidentRepositoryCustom {

    BirthReportResidentResponseDto findReportResident(Integer residentSerialNumber);
}
