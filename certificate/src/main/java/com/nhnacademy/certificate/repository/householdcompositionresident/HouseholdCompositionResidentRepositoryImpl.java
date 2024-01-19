package com.nhnacademy.certificate.repository.householdcompositionresident;

import com.nhnacademy.certificate.domain.ResidentResponseDto;
import com.nhnacademy.certificate.entity.HouseholdCompositionResident;
import com.nhnacademy.certificate.entity.QHousehold;
import com.nhnacademy.certificate.entity.QHouseholdCompositionResident;
import com.nhnacademy.certificate.entity.QResident;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class HouseholdCompositionResidentRepositoryImpl extends QuerydslRepositorySupport
        implements HouseholdCompositionResidentRepositoryCustom {

    public HouseholdCompositionResidentRepositoryImpl() {
        super(HouseholdCompositionResident.class);
    }


    @Override
    public List<ResidentResponseDto> findResidentsBySerialNumber(Integer serialNumber) {


        QResident resident = QResident.resident;
        QHouseholdCompositionResident householdCompositionResident =
                QHouseholdCompositionResident.householdCompositionResident;
        QHousehold household = QHousehold.household;

        return from(householdCompositionResident)
                .innerJoin(householdCompositionResident.household, household)
                .innerJoin(householdCompositionResident.resident, resident)
                .where(household.householdSerialNumber.eq(serialNumber))
                .select(Projections.constructor(ResidentResponseDto.class,
                        householdCompositionResident.householdRelationshipCode,
                        resident.name,
                        resident.residentRegistrationNumber,
                        householdCompositionResident.reportDate,
                        householdCompositionResident.householdCompositionChangeReasonCode))
                .fetch();
    }
}
