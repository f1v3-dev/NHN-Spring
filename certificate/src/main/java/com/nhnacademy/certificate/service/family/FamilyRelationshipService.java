package com.nhnacademy.certificate.service.family;

import com.nhnacademy.certificate.domain.ParentResponseDto;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import java.util.List;

public interface FamilyRelationshipService {

    List<FamilyRelationship> findDistinct();

    List<ParentResponseDto> findParents(Integer residentSerialNumber);
}
