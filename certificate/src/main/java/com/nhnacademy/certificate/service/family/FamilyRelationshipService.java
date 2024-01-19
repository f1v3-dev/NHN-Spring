package com.nhnacademy.certificate.service.family;

import com.nhnacademy.certificate.entity.FamilyRelationship;
import java.util.List;

public interface FamilyRelationshipService {

    List<FamilyRelationship> findDistinct();
}
