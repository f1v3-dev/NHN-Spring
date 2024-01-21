package com.nhnacademy.certificate.repository.householdmovementaddress;

import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdMovementAddressRepository
        extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {


    @Modifying
    @Query("UPDATE HouseholdMovementAddress h " +
            "SET h.lastAddressYn = 'N' " +
            "WHERE h.lastAddressYn = 'Y' " +
            " AND h.pk.householdSerialNumber = ?1")
    void updateLastAddress(Integer householdSerialNumber);

    @Query("SELECT COUNT(h) " +
            "FROM HouseholdMovementAddress h " +
            "WHERE h.pk = ?1")
    Long countByPk(HouseholdMovementAddress.Pk pk);
}
