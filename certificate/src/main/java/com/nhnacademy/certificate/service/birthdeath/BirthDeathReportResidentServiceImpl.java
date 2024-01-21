package com.nhnacademy.certificate.service.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.domain.DeathReportResidentResponseDto;
import com.nhnacademy.certificate.domain.rest.BirthRequestDto;
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
    public BirthDeathReportResident registerBirth(Integer reportSerialNumber, BirthRequestDto birthReport) {

        Resident birthResident = residentRepository.findById(birthReport.getTargetSerialNumber())
                .orElseThrow(ResidentNotFoundException::new);

        Resident reportResident = residentRepository.findById(reportSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);


        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode("출생");
        pk.setReportResidentSerialNumber(reportResident.getResidentSerialNumber());
        pk.setResidentSerialNumber(birthReport.getTargetSerialNumber());


        if (birthDeathRepository.existsByPk_ResidentSerialNumberAndPk_BirthDeathTypeCode
                (pk.getResidentSerialNumber(), pk.getBirthDeathTypeCode())) {
            throw new IllegalArgumentException("Already registered birth report!");
        }

        BirthDeathReportResident birthReportResident = new BirthDeathReportResident();
        birthReportResident.setResident(birthResident);
        birthReportResident.setBirthDeathReportDate(LocalDate.now());
        birthReportResident.setBirthReportQualificationsCode(birthReport.getBirthReportQualificationsCode());
        birthReportResident.setEmailAddress(birthReport.getEmailAddress());
        birthReportResident.setPhoneNumber(birthReport.getPhoneNumber());


        birthReportResident.setPk(pk);

        return birthDeathRepository.save(birthReportResident);
    }

    @Override
    public BirthDeathReportResident modify(Integer reportSerialNumber, BirthRequestDto birthReport) {

        Resident birthResident = residentRepository.findById(birthReport.getTargetSerialNumber())
                .orElseThrow(ResidentNotFoundException::new);

        Resident reportResident = residentRepository.findById(reportSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode("출생");
        pk.setReportResidentSerialNumber(reportResident.getResidentSerialNumber());
        pk.setResidentSerialNumber(birthResident.getResidentSerialNumber());

        if (!birthDeathRepository.existsById(pk)) {
            throw new IllegalArgumentException("Not registered birth report!");
        }

        BirthDeathReportResident birthReportResident = new BirthDeathReportResident();
        birthReportResident.setResident(birthResident);
        birthReportResident.setBirthDeathReportDate(LocalDate.now());
        birthReportResident.setBirthReportQualificationsCode(birthReport.getBirthReportQualificationsCode());
        birthReportResident.setEmailAddress(birthReport.getEmailAddress());
        birthReportResident.setPhoneNumber(birthReport.getPhoneNumber());
        birthReportResident.setPk(pk);

        return birthDeathRepository.save(birthReportResident);
    }

    @Override
    public void deleteBirthReport(Integer reportSerialNumber, Integer targetSerialNumber) {

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setBirthDeathTypeCode("출생");
        pk.setReportResidentSerialNumber(reportSerialNumber);
        pk.setResidentSerialNumber(targetSerialNumber);

        if (!birthDeathRepository.existsById(pk)) {
            throw new IllegalArgumentException("Not registered birth report!");
        }

        birthDeathRepository.deleteById(pk);
    }
}
