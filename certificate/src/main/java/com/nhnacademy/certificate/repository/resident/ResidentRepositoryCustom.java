package com.nhnacademy.certificate.repository.resident;

import com.nhnacademy.certificate.domain.ResidentListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ResidentRepositoryCustom {

    Page<ResidentListDto> findList(Pageable pageable);
}
