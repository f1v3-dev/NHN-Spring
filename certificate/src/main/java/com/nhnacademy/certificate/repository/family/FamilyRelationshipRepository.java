package com.nhnacademy.certificate.repository.family;

import com.nhnacademy.certificate.domain.ParentResponseDto;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    List<FamilyRelationship> findDistinctBy();

    @Query("SELECT new com.nhnacademy.certificate.domain.ParentResponseDto(f.familyRelationshipCode, r.name, r.residentRegistrationNumber) " +
            "FROM Resident r " +
            " JOIN FamilyRelationship f " +
            " ON r.residentSerialNumber = f.pk.familyResidentSerialNumber " +
            "WHERE f.pk.baseResidentSerialNumber = ?1 " +
            "AND f.familyRelationshipCode IN ('부', '모')")
    List<ParentResponseDto> findParents(Integer residentSerialNumber);

    @Query("SELECT COUNT(f) > 0 " +
            "FROM FamilyRelationship f " +
            "WHERE f.pk.familyResidentSerialNumber = ?1")
    boolean findByFamilySerialNumber(Integer familySerialNumber);
}
