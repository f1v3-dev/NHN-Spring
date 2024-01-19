package com.nhnacademy.certificate.repository.householdcompositionresident;

import com.nhnacademy.certificate.domain.ResidentResponseDto;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdCompositionResidentRepositoryCustom {

    List<ResidentResponseDto> findResidentsBySerialNumber(Integer serialNumber);
}
