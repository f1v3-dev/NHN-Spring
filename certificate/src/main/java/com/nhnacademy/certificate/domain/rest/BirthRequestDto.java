package com.nhnacademy.certificate.domain.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BirthRequestDto {


    @JsonProperty("target_serial_number")
    private Integer targetSerialNumber;

    @JsonProperty("birth_report_qualifications_code")
    private String birthReportQualificationsCode;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("phone_number")
    private String phoneNumber;

}
