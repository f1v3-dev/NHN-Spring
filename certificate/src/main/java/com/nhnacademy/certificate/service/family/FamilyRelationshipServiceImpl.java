package com.nhnacademy.certificate.service.family;

import com.nhnacademy.certificate.domain.ParentResponseDto;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import com.nhnacademy.certificate.repository.family.FamilyRelationshipRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {

    public FamilyRelationshipServiceImpl(FamilyRelationshipRepository familyRelationshipRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
    }

    private final FamilyRelationshipRepository familyRelationshipRepository;


    @Override
    public List<FamilyRelationship> findDistinct() {
        return familyRelationshipRepository.findDistinctBy();
    }

    @Override
    public List<ParentResponseDto> findParents(Integer residentSerialNumber) {
        return familyRelationshipRepository.findParents(residentSerialNumber);
    }
}
