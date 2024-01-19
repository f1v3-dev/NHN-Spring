package com.nhnacademy.certificate.service.householdcompositionresident;

import com.nhnacademy.certificate.domain.ResidentResponseDto;
import com.nhnacademy.certificate.entity.HouseholdCompositionResident;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.repository.householdcompositionresident.HouseholdCompositionResidentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HouseholdCompositionResidentServiceImpl implements HouseholdCompositionResidentService {

    private final HouseholdCompositionResidentRepository repository;

    public HouseholdCompositionResidentServiceImpl(
            HouseholdCompositionResidentRepository householdCompositionResidentRepository) {
        this.repository = householdCompositionResidentRepository;
    }


    @Override
    public List<ResidentResponseDto> findResidentsBySerialNumber(Integer serialNumber) {
        return repository.findResidentsBySerialNumber(serialNumber);
    }

}
