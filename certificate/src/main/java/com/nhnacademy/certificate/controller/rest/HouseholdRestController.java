package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.rest.HouseholdRequestDto;
import com.nhnacademy.certificate.service.household.HouseholdService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household")
public class HouseholdRestController {

    private final HouseholdService householdService;


    public HouseholdRestController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerHousehold(@RequestBody HouseholdRequestDto household) {

        householdService.register(household);
    }

    @DeleteMapping("/{householdSerialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHousehold(@PathVariable("householdSerialNumber") Integer householdSerialNumber) {
        householdService.delete(householdSerialNumber);
    }
}
