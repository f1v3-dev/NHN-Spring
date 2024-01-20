package com.nhnacademy.certificate.repository.birthdeath;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
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
    public BirthReportResidentResponseDto findReportResident(Integer residentSerialNumber) {

        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QResident resident = QResident.resident;


        /**
         * SELECT *
         * FROM birth_death_report_resident AS bdr
         *  INNER JOIN resident AS r
         *  ON bdr.report_resident_serial_number = r.resident_serial_number
         * WHERE bdr.resident_serial_number = 7 AND bdr.birth_death_type_code = '출생';
         */

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
}
