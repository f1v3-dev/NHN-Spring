package com.nhnacademy.certificate.domain.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeathRequestDto {


    @JsonProperty("target_serial_number")
    private Integer targetSerialNumber;

    @JsonProperty("report_qualifications_code")
    private String reportQualificationsCode;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
