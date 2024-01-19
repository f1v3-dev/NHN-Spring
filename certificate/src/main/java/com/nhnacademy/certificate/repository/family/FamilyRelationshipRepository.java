package com.nhnacademy.certificate.repository.family;

import com.nhnacademy.certificate.entity.FamilyRelationship;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    List<FamilyRelationship> findDistinctBy();

}
