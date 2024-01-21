package com.nhnacademy.certificate.service.resident;

import com.nhnacademy.certificate.domain.ResidentBirthDto;
import com.nhnacademy.certificate.domain.ResidentDeathDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.domain.ResidentFamilyDto;
import com.nhnacademy.certificate.domain.ResidentListDto;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.HeadOfHouseholdDeletionException;
import com.nhnacademy.certificate.repository.resident.ResidentRepository;
import com.nhnacademy.certificate.service.household.HouseholdService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;

    private final HouseholdService householdService;

    public ResidentServiceImpl(ResidentRepository residentRepository, HouseholdService householdService) {
        this.residentRepository = residentRepository;
        this.householdService = householdService;
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

    @Override
    public void deleteResident(Integer residentSerialNumber) {

        if (householdService. isHeadOfHousehold(residentSerialNumber)) {
            throw new HeadOfHouseholdDeletionException();
        }

        residentRepository.deleteById(residentSerialNumber);
    }
}
