package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.rest.MovementAddressRequestDto;
import com.nhnacademy.certificate.service.householdmovementaddress.HouseholdMovementAddressService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household/{householdSerialNumber}/movement")
public class MovementRestController {

    private final HouseholdMovementAddressService householdMovementAddressService;


    public MovementRestController(HouseholdMovementAddressService householdMovementAddressService) {
        this.householdMovementAddressService = householdMovementAddressService;
    }

    @PostMapping
    public void registerMovementAddress(@PathVariable("householdSerialNumber") Integer serialNumber,
                                        @RequestBody MovementAddressRequestDto requestDto) {

        requestDto.setHouseholdSerialNumber(serialNumber);
        householdMovementAddressService.register(requestDto);
    }

    @PutMapping("/{reportDate}")
    public void modifyMovementAddress(@PathVariable("householdSerialNumber") Integer serialNumber,
                                      @PathVariable("reportDate") String reportDate,
                                      @RequestBody MovementAddressRequestDto requestDto) {

        // 20240101 -> 2024-01-01

        LocalDate houseMovementReportDate = LocalDate.parse(reportDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        requestDto.setHouseholdSerialNumber(serialNumber);
        requestDto.setHouseMovementReportDate(houseMovementReportDate);

        householdMovementAddressService.modify(requestDto);
    }

    @DeleteMapping("/{reportDate}")
    public void deleteMovementAddress(@PathVariable("householdSerialNumber") Integer serialNumber,
                                      @PathVariable("reportDate") String reportDate) {

        LocalDate houseMovementReportDate = LocalDate.parse(reportDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        householdMovementAddressService.delete(serialNumber, houseMovementReportDate);
    }
}
