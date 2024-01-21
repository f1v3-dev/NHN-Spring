package com.nhnacademy.certificate.repository.birthdeath;

import com.nhnacademy.certificate.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository
        extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk>,
        BirthDeathReportResidentRepositoryCustom {

    boolean existsByPk_ResidentSerialNumberAndPk_BirthDeathTypeCode(Integer residentSerialNumber, String birthDeathTypeCode);
}
