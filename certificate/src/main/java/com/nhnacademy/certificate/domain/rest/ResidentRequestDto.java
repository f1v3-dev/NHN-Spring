package com.nhnacademy.certificate.domain.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResidentRequestDto {

    @JsonProperty("resident_serial_number")
    private Integer residentSerialNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("resident_registration_number")
    private String residentRegistrationNumber;

    @JsonProperty("gender_code")
    private String genderCode;

    @JsonProperty("birth_date")
    private LocalDateTime birthDate;

    @JsonProperty("birth_place_code")
    private String birthPlaceCode;

    @JsonProperty("registration_base_address")
    private String registrationBaseAddress;

    @JsonProperty("death_date")
    private LocalDateTime deathDate;

    @JsonProperty("death_place_code")
    private String deathPlaceCode;

    @JsonProperty("death_place_address")
    private String deathPlaceAddress;

}
