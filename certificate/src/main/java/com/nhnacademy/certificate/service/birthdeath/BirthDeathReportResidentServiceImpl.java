package com.nhnacademy.certificate.service.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.domain.DeathReportResidentResponseDto;
import com.nhnacademy.certificate.domain.rest.BirthDeathRequestDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.repository.birthdeath.BirthDeathReportResidentRepository;
import com.nhnacademy.certificate.repository.resident.ResidentRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class BirthDeathReportResidentServiceImpl implements BirthDeathReportResidentService {

    private final BirthDeathReportResidentRepository birthDeathRepository;

    private final ResidentRepository residentRepository;

    public BirthDeathReportResidentServiceImpl(BirthDeathReportResidentRepository birthDeathRepository,
                                               ResidentRepository residentService) {
        this.birthDeathRepository = birthDeathRepository;
        this.residentRepository = residentService;
    }

    @Override
    public BirthReportResidentResponseDto findBirthReportResident(Integer residentSerialNumber) {
        return birthDeathRepository.findBirthReportResident(residentSerialNumber);
    }

    @Override
    public DeathReportResidentResponseDto findDeathReportResident(Integer residentSerialNumber) {
        return birthDeathRepository.findDeathReportResident(residentSerialNumber);
    }

    @Override
    public BirthDeathReportResident register(Integer reportSerialNumber, BirthDeathRequestDto report,
                                             String birthDeathTypeCode) {
        Resident birthResident = residentRepository.findById(report.getTargetSerialNumber())
                .orElseThrow(ResidentNotFoundException::new);

        Resident reportResident = residentRepository.findById(reportSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);


        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode(birthDeathTypeCode);
        pk.setReportResidentSerialNumber(reportResident.getResidentSerialNumber());
        pk.setResidentSerialNumber(report.getTargetSerialNumber());


        if (birthDeathRepository.existsByPk_ResidentSerialNumberAndPk_BirthDeathTypeCode
                (pk.getResidentSerialNumber(), pk.getBirthDeathTypeCode())) {
            throw new IllegalArgumentException("Already registered birth report!");
        }

        return getSavedReportResident(report, birthDeathTypeCode, birthResident, pk);

    }

    @Override
    public BirthDeathReportResident modify(Integer reportSerialNumber, BirthDeathRequestDto report,
                                           String birthDeathTypeCode) {
        Resident birthResident = residentRepository.findById(report.getTargetSerialNumber())
                .orElseThrow(ResidentNotFoundException::new);

        Resident reportResident = residentRepository.findById(reportSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode(birthDeathTypeCode);
        pk.setReportResidentSerialNumber(reportResident.getResidentSerialNumber());
        pk.setResidentSerialNumber(birthResident.getResidentSerialNumber());

        if (!birthDeathRepository.existsByPk_ResidentSerialNumberAndPk_BirthDeathTypeCode(pk.getResidentSerialNumber(),
                pk.getBirthDeathTypeCode())) {
            throw new IllegalArgumentException("Not registered birth report!");
        }

        return getSavedReportResident(report, birthDeathTypeCode, birthResident, pk);
    }

    private BirthDeathReportResident getSavedReportResident(BirthDeathRequestDto report, String birthDeathTypeCode,
                                                            Resident birthResident,
                                                            BirthDeathReportResident.Pk pk) {
        BirthDeathReportResident birthReportResident = new BirthDeathReportResident();
        birthReportResident.setResident(birthResident);
        birthReportResident.setBirthDeathReportDate(LocalDate.now());
        birthReportResident.setEmailAddress(report.getEmailAddress());
        birthReportResident.setPhoneNumber(report.getPhoneNumber());
        birthReportResident.setPk(pk);

        if (birthDeathTypeCode.equals("출생")) {
            birthReportResident.setBirthReportQualificationsCode(report.getReportQualificationsCode());
        } else {
            birthReportResident.setDeathReportQualificationsCode(report.getReportQualificationsCode());
        }

        return birthDeathRepository.save(birthReportResident);
    }


    @Override
    public void delete(Integer reportSerialNumber, Integer targetSerialNumber, String birthDeathTypeCode) {

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode(birthDeathTypeCode);
        pk.setReportResidentSerialNumber(reportSerialNumber);
        pk.setResidentSerialNumber(targetSerialNumber);

        if (!birthDeathRepository.existsById(pk)) {
            throw new IllegalArgumentException("Not registered birth report!");
        }

        birthDeathRepository.deleteById(pk);
    }
}
