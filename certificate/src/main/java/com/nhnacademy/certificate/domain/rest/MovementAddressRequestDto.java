package com.nhnacademy.certificate.domain.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovementAddressRequestDto {

    @JsonProperty("house_movement_report_date")
    private LocalDate houseMovementReportDate;

    @JsonProperty("household_serial_number")
    private Integer householdSerialNumber;

    @JsonProperty("house_movement_address")
    private String houseMovementAddress;

    @JsonProperty("last_address_yn")
    private String lastAddressYn;

}
