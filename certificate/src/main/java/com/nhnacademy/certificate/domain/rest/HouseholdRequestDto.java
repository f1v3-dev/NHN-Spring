package com.nhnacademy.certificate.domain.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HouseholdRequestDto {

    @JsonProperty("household_serial_number")
    private Integer householdSerialNumber;

    @JsonProperty("household_resident_serial_number")
    private Integer householdHeadSerialNumber;

    @JsonProperty("household_composition_date")
    private LocalDate householdCompositionDate;

    @JsonProperty("household_composition_reason_code")
    private String householdCompositionReasonCode;

    @JsonProperty("current_house_momvement_address")
    private String currentHouseMovementAddress;

    @JsonProperty("household_composition_resident")
    private HouseholdCompositionResidentDto householdCompositionResidentDto;

    @Getter
    public static class HouseholdCompositionResidentDto {

        @JsonProperty("household_serial_number")
        private Integer householdSerialNumber;

        @JsonProperty("resident_serial_number")
        private Integer residentSerialNumber;

        @JsonProperty("report_date")
        private LocalDate reportDate;

        @JsonProperty("household_relationship_code")
        private String householdRelationshipCode;

        @JsonProperty("household_composition_change_reason_code")
        private String householdCompositionChangeReasonCode;
    }

}
