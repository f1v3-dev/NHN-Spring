package com.nhnacademy.certificate.repository.household;

import com.nhnacademy.certificate.entity.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {

    Household findByHouseholdSerialNumber(Integer serialNumber);

    @Query("SELECT count(h) > 0 " +
            "FROM Household h " +
            "WHERE h.resident.residentSerialNumber = ?1")
    boolean isHeadOfHousehold(Integer residentSerialNumber);


    Long countByHouseholdSerialNumber(Integer householdSerialNumber);
}
