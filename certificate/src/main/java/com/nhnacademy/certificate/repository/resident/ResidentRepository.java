package com.nhnacademy.certificate.repository.resident;

import com.nhnacademy.certificate.domain.ResidentBirthDto;
import com.nhnacademy.certificate.domain.ResidentDeathDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Integer>,
        ResidentRepositoryCustom {

    ResidentDto findByResidentSerialNumber(Integer residentSerialNumber);

    ResidentBirthDto findBirthResidentByResidentSerialNumber(Integer residentSerialNumber);

    ResidentDeathDto findDeathResidentByResidentSerialNumber(Integer residentSerialNumber);
}
