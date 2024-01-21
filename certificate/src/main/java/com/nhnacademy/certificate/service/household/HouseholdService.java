package com.nhnacademy.certificate.service.household;

import com.nhnacademy.certificate.entity.Household;

public interface HouseholdService {

    Household findBySerialNumber(Integer serialNumber);

    boolean isHeadOfHousehold(Integer residentSerialNumber);
}
