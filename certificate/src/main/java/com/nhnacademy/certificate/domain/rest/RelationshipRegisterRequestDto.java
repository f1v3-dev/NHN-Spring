package com.nhnacademy.certificate.domain.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelationshipRegisterRequestDto {


    @JsonProperty("family_serial_number")
    Integer familySerialNumber;

    @JsonProperty("relationship")
    String relationship;
}
