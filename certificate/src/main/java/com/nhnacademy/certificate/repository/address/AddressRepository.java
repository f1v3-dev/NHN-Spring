package com.nhnacademy.certificate.repository.address;

import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {

    @Query("select hma from HouseholdMovementAddress hma " +
            "where hma.pk.householdSerialNumber = ?1 " +
            "order by hma.pk.houseMovementReportDate desc")
    List<HouseholdMovementAddress> findBySerialNumber(Integer serialNumber);
}
