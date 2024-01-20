package com.nhnacademy.certificate.service.resident;

import com.nhnacademy.certificate.domain.ResidentBirthDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.domain.ResidentFamilyDto;
import com.nhnacademy.certificate.domain.ResidentListDto;
import com.nhnacademy.certificate.entity.Resident;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {

    Page<Resident> findAll(Pageable pageable);

    Page<ResidentListDto> findList(Pageable pageable);

    ResidentDto findById(Integer residentSerialNumber);

    List<ResidentFamilyDto> findFamilyById(Integer residentSerialNumber);

    ResidentBirthDto findBirthResident(Integer residentSerialNumber);
}
