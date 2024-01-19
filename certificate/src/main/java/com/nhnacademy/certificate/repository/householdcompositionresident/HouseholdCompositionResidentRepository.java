package com.nhnacademy.certificate.repository.householdcompositionresident;

import com.nhnacademy.certificate.entity.HouseholdCompositionResident;
import com.nhnacademy.certificate.entity.Resident;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdCompositionResidentRepository
        extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk>,
        HouseholdCompositionResidentRepositoryCustom {

    @Query("select r " +
            "from HouseholdCompositionResident hcr " +
            "left join Resident r " +
            "on hcr.pk.residentSerialNumber = r.residentSerialNumber")
    List<Resident> findAllResidents();
}
