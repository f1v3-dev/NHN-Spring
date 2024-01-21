package com.nhnacademy.certificate.service.household;

import com.nhnacademy.certificate.domain.rest.HouseholdRequestDto;
import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.entity.HouseholdCompositionResident;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.HouseholdNotFoundException;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.repository.household.HouseholdRepository;
import com.nhnacademy.certificate.repository.householdcompositionresident.HouseholdCompositionResidentRepository;
import com.nhnacademy.certificate.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    private final ResidentRepository residentRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository,
                                HouseholdCompositionResidentRepository householdCompositionResidentRepository,
                                ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    public Household findBySerialNumber(Integer serialNumber) {
        return householdRepository.findByHouseholdSerialNumber(serialNumber);
    }

    @Override
    public boolean isHeadOfHousehold(Integer residentSerialNumber) {
        return householdRepository.isHeadOfHousehold(residentSerialNumber);
    }

    @Transactional
    @Override
    public Household register(HouseholdRequestDto householdDto) {

        Resident resident = residentRepository.findById(householdDto.getHouseholdSerialNumber())
                .orElseThrow(ResidentNotFoundException::new);

        Household household = new Household();
        household.setResident(resident);
        household.setHouseholdSerialNumber(householdDto.getHouseholdSerialNumber());
        household.setHouseholdCompositionDate(householdDto.getHouseholdCompositionDate());
        household.setHouseholdCompositionReasonCode(householdDto.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(householdDto.getCurrentHouseMovementAddress());


        Household savedHousehold = householdRepository.save(household);

        HouseholdRequestDto.HouseholdCompositionResidentDto hcrDto = householdDto.getHouseholdCompositionResidentDto();

        HouseholdCompositionResident hcr = new HouseholdCompositionResident();
        hcr.setHousehold(household);
        hcr.setResident(resident);
        hcr.setReportDate(hcrDto.getReportDate());
        hcr.setHouseholdRelationshipCode(hcrDto.getHouseholdRelationshipCode());
        hcr.setHouseholdCompositionChangeReasonCode(hcrDto.getHouseholdCompositionChangeReasonCode());

        HouseholdCompositionResident.Pk pk = new HouseholdCompositionResident.Pk();
        pk.setHouseholdSerialNumber(household.getHouseholdSerialNumber());
        pk.setResidentSerialNumber(resident.getResidentSerialNumber());
        hcr.setPk(pk);

        householdCompositionResidentRepository.save(hcr);


        return savedHousehold;
    }

    @Override
    public void delete(Integer householdSerialNumber) {
        if (householdRepository.countByHouseholdSerialNumber(householdSerialNumber) < 1) {
            throw new HouseholdNotFoundException();
        }

        householdRepository.deleteById(householdSerialNumber);
    }
}
