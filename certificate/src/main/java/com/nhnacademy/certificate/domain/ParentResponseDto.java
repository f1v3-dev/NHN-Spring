package com.nhnacademy.certificate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
public class ParentResponseDto {

    String relationshipCode;

    String name;

    String residentRegistrationNumber;
}
