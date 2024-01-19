package com.nhnacademy.certificate.service.resident;

import com.nhnacademy.certificate.domain.ResidentListDto;
import com.nhnacademy.certificate.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentService {

    Page<Resident> findAll(Pageable pageable);

    Page<ResidentListDto> findList(Pageable pageable);
}
