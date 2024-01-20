package com.nhnacademy.certificate.repository.family;

import com.nhnacademy.certificate.domain.ParentResponseDto;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    List<FamilyRelationship> findDistinctBy();


    /*
    SELECT r.*
    FROM resident r
        JOIN family_relationship f
        ON r.resident_serial_number = f.family_resident_serial_number
    WHERE f.base_resident_serial_number = 7
    AND f.family_relationship_code IN ('부', '모');
     */
    @Query("SELECT new com.nhnacademy.certificate.domain.ParentResponseDto(f.familyRelationshipCode, r.name, r.residentRegistrationNumber) " +
            "FROM Resident r " +
            " JOIN FamilyRelationship f " +
            " ON r.residentSerialNumber = f.pk.familyResidentSerialNumber " +
            "WHERE f.pk.baseResidentSerialNumber = ?1 " +
            "AND f.familyRelationshipCode IN ('부', '모')")
    List<ParentResponseDto> findParents(Integer residentSerialNumber);
}
