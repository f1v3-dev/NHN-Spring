package com.nhnacademy.certificate.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResidentFamilyDto {

    /**
     * familyRelationship.familyRelationshipCode,
     *                         resident.name,
     *                         resident.birthDate,
     *                         resident.residentRegistrationNumber,
     *                         resident.genderCode
     */

    private String familyRelationshipCode;

    private String name;

    private LocalDateTime birthDate;

    private String residentRegistrationNumber;

    private String genderCode;
}
