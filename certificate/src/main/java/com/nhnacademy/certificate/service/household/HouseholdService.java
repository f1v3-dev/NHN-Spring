package com.nhnacademy.certificate.service.household;

import com.nhnacademy.certificate.domain.rest.HouseholdRequestDto;
import com.nhnacademy.certificate.entity.Household;

public interface HouseholdService {

    Household findBySerialNumber(Integer serialNumber);

    boolean isHeadOfHousehold(Integer residentSerialNumber);

    Household register(HouseholdRequestDto householdDto);

    void delete(Integer householdSerialNumber);
}
