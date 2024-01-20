package com.nhnacademy.certificate.service.resident;

import com.nhnacademy.certificate.domain.ResidentBirthDto;
import com.nhnacademy.certificate.domain.ResidentDeathDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.domain.ResidentFamilyDto;
import com.nhnacademy.certificate.domain.ResidentListDto;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.resident.ResidentRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public Page<Resident> findAll(Pageable pageable) {
        return residentRepository.findAll(pageable);

    }

    @Override
    public Page<ResidentListDto> findList(Pageable pageable) {
        return residentRepository.findList(pageable);
    }

    @Override
    public ResidentDto findById(Integer residentSerialNumber) {
        return residentRepository.findByResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public List<ResidentFamilyDto> findFamilyById(Integer residentSerialNumber) {
        return residentRepository.findFamilyById(residentSerialNumber);
    }

    @Override
    public ResidentBirthDto findBirthResident(Integer residentSerialNumber) {
        return residentRepository.findBirthResidentByResidentSerialNumber(residentSerialNumber);
    }

    @Override
    public ResidentDeathDto findDeathResident(Integer residentSerialNumber) {
        return residentRepository.findDeathResidentByResidentSerialNumber(residentSerialNumber);
    }
}
