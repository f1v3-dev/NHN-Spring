package com.nhnacademy.certificate.service.resident;

import com.nhnacademy.certificate.domain.ResidentListDto;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.resident.ResidentRepository;
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
}
