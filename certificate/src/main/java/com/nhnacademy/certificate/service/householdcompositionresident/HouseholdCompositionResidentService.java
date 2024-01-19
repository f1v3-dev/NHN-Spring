package com.nhnacademy.certificate.service.householdcompositionresident;

import com.nhnacademy.certificate.domain.ResidentResponseDto;
import java.util.List;

public interface HouseholdCompositionResidentService {

    List<ResidentResponseDto> findResidentsBySerialNumber(Integer serialNumber);


}
