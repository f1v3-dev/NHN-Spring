package com.nhnacademy.certificate.service.household;

import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.repository.household.HouseholdRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public Household findBySerialNumber(Integer serialNumber) {
        return householdRepository.findByHouseholdSerialNumber(serialNumber);
    }
}
