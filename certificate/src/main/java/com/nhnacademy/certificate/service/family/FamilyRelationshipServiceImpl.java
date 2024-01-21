package com.nhnacademy.certificate.service.family;

import com.nhnacademy.certificate.domain.ParentResponseDto;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentNotFoundException;
import com.nhnacademy.certificate.repository.family.FamilyRelationshipRepository;
import com.nhnacademy.certificate.repository.resident.ResidentRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;

    private final ResidentRepository residentRepository;

    public FamilyRelationshipServiceImpl(FamilyRelationshipRepository familyRelationshipRepository,
                                         ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }


    @Override
    public List<FamilyRelationship> findDistinct() {
        return familyRelationshipRepository.findDistinctBy();
    }

    @Override
    public List<ParentResponseDto> findParents(Integer residentSerialNumber) {
        return familyRelationshipRepository.findParents(residentSerialNumber);
    }

    @Transactional
    @Override
    public FamilyRelationship register(Integer residentSerialNumber, Integer familySerialNumber, String relationship) {

        if (residentSerialNumber.equals(familySerialNumber)) {
            throw new IllegalArgumentException("Cannot register yourself.");
        }

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(familySerialNumber, residentSerialNumber);
        if (familyRelationshipRepository.existsById(pk)) {
            throw new IllegalArgumentException("You are already registered.");
        }


        Resident resident = residentRepository.findById(residentSerialNumber)
                .orElseThrow(ResidentNotFoundException::new);

        return familyRelationshipRepository.save(new FamilyRelationship(pk, resident, relationship));

    }

    @Transactional
    @Override
    public FamilyRelationship modify(Integer residentSerialNumber, Integer familySerialNumber, String relationship) {

        if (residentSerialNumber.equals(familySerialNumber)) {
            throw new IllegalArgumentException("Cannot register yourself.");
        }

        if (!(residentRepository.existsById(residentSerialNumber) &&
                residentRepository.existsById(familySerialNumber))) {
            throw new IllegalArgumentException("cannot find resident.");
        }

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(familySerialNumber, residentSerialNumber);

        if (!familyRelationshipRepository.existsById(pk)) {
            throw new IllegalArgumentException("cannot find relationship.");
        }


        FamilyRelationship familyRelationship = new FamilyRelationship();
        familyRelationship.setPk(pk);
        familyRelationship.setFamilyRelationshipCode(relationship);

        return familyRelationshipRepository.save(familyRelationship);

    }


    @Transactional
    @Override
    public void deleteById(Integer residentSerialNumber, Integer familySerialNumber) {
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(familySerialNumber, residentSerialNumber);

        if (!familyRelationshipRepository.existsById(pk)) {
            throw new IllegalArgumentException("cannot find relationship.");
        }

        familyRelationshipRepository.deleteById(pk);

    }

}
