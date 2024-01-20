package com.nhnacademy.certificate.service.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.repository.birthdeath.BirthDeathReportResidentRepository;
import org.springframework.stereotype.Service;

@Service
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService {

    private final BirthDeathReportResidentRepository birthDeathRepository;

    public BirthDeathReportResidentServiceImpl(BirthDeathReportResidentRepository birthDeathRepository) {
        this.birthDeathRepository = birthDeathRepository;
    }

    @Override
    public BirthReportResidentResponseDto findReportResident(Integer residentSerialNumber) {
        return birthDeathRepository.findReportResident(residentSerialNumber);
    }
}
