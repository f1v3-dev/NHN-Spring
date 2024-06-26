package com.nhnacademy.certificate.repository.resident;

import com.nhnacademy.certificate.domain.ResidentFamilyDto;
import com.nhnacademy.certificate.domain.ResidentListDto;
import com.nhnacademy.certificate.entity.QBirthDeathReportResident;
import com.nhnacademy.certificate.entity.QCertificateIssue;
import com.nhnacademy.certificate.entity.QFamilyRelationship;
import com.nhnacademy.certificate.entity.QHousehold;
import com.nhnacademy.certificate.entity.QHouseholdCompositionResident;
import com.nhnacademy.certificate.entity.QResident;
import com.nhnacademy.certificate.entity.Resident;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport
        implements ResidentRepositoryCustom {

    public ResidentRepositoryImpl() {
        super(Resident.class);
    }

    @Override
    public Page<ResidentListDto> findList(Pageable pageable) {

        QResident resident = QResident.resident;
        QHouseholdCompositionResident householdCompositionResident =
                QHouseholdCompositionResident.householdCompositionResident;
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QCertificateIssue certificateIssue = QCertificateIssue.certificateIssue;
        QHousehold household = QHousehold.household;


        List<ResidentListDto> residentLists = from(resident)
                .leftJoin(resident.householdCompositionResidents, householdCompositionResident)
                .leftJoin(householdCompositionResident.household, household)
                .leftJoin(resident.birthDeathReportResidents, birthDeathReportResident)
                .leftJoin(resident.certificateIssues, certificateIssue)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .select(Projections.constructor(ResidentListDto.class,
                        resident.residentSerialNumber,
                        resident.name,
                        household.householdSerialNumber,
                        birthDeathReportResident.pk.birthDeathTypeCode,
                        certificateIssue.resident.residentSerialNumber))
                .distinct()
                .fetch();

        long count = from(resident)
                .fetchCount();

        return new PageImpl<>(residentLists, pageable, count);
    }

    @Override
    public List<ResidentFamilyDto> findFamilyById(Integer residentSerialNumber) {
        QResident resident = QResident.resident;
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;


        List<ResidentFamilyDto> dtoList = from(familyRelationship)
                .join(resident)
                .on(familyRelationship.pk.familyResidentSerialNumber.eq(resident.residentSerialNumber))
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(residentSerialNumber))
                .select(Projections.constructor(ResidentFamilyDto.class,
                        familyRelationship.familyRelationshipCode,
                        resident.name,
                        resident.birthDate,
                        resident.residentRegistrationNumber,
                        resident.genderCode))
                .fetch();

        return dtoList;
    }
}
