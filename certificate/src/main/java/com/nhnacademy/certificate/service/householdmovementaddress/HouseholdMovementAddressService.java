package com.nhnacademy.certificate.service.householdmovementaddress;

import com.nhnacademy.certificate.domain.rest.MovementAddressRequestDto;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import java.time.LocalDate;

public interface HouseholdMovementAddressService {
    HouseholdMovementAddress register(MovementAddressRequestDto requestDto);

    HouseholdMovementAddress modify(MovementAddressRequestDto requestDto);

    void delete(Integer serialNumber, LocalDate houseMovementReportDate);
}
