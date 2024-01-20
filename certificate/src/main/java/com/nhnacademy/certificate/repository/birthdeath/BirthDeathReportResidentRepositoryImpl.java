package com.nhnacademy.certificate.repository.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.domain.DeathReportResidentResponseDto;
import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import com.nhnacademy.certificate.entity.QBirthDeathReportResident;
import com.nhnacademy.certificate.entity.QResident;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BirthDeathReportResidentRepositoryImpl extends QuerydslRepositorySupport
        implements BirthDeathReportResidentRepositoryCustom {

    public BirthDeathReportResidentRepositoryImpl() {
        super(BirthDeathReportResident.class);
    }

    @Override
    public BirthReportResidentResponseDto findBirthReportResident(Integer residentSerialNumber) {

        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QResident resident = QResident.resident;

        return from(birthDeathReportResident)
                .innerJoin(resident)
                .on(birthDeathReportResident.pk.reportResidentSerialNumber.eq(resident.residentSerialNumber))
                .where(birthDeathReportResident.pk.residentSerialNumber.eq(residentSerialNumber)
                        .and(birthDeathReportResident.pk.birthDeathTypeCode.eq("출생")))
                .select(Projections.constructor(BirthReportResidentResponseDto.class,
                        birthDeathReportResident.emailAddress,
                        birthDeathReportResident.phoneNumber,
                        birthDeathReportResident.birthReportQualificationsCode,
                        resident.name,
                        resident.residentRegistrationNumber,
                        birthDeathReportResident.birthDeathReportDate))
                .fetchOne();
    }

    @Override
    public DeathReportResidentResponseDto findDeathReportResident(Integer residentSerialNumber) {

        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QResident resident = QResident.resident;

        return from(birthDeathReportResident)
                .innerJoin(resident)
                .on(birthDeathReportResident.pk.reportResidentSerialNumber.eq(resident.residentSerialNumber))
                .where(birthDeathReportResident.pk.residentSerialNumber.eq(residentSerialNumber)
                        .and(birthDeathReportResident.pk.birthDeathTypeCode.eq("사망")))
                .select(Projections.constructor(DeathReportResidentResponseDto.class,
                        resident.name,
                        resident.residentRegistrationNumber,
                        birthDeathReportResident.deathReportQualificationsCode,
                        birthDeathReportResident.emailAddress,
                        birthDeathReportResident.phoneNumber,
                        birthDeathReportResident.birthDeathReportDate))
                .fetchOne();
    }
}
