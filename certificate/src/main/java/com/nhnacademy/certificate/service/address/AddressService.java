package com.nhnacademy.certificate.service.address;

import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import java.util.List;

public interface AddressService {
    List<HouseholdMovementAddress> findBySerialNumber(Integer serialNumber);
}
