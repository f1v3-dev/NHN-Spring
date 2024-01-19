package com.nhnacademy.certificate.controller.copy;

import com.nhnacademy.certificate.domain.ResidentResponseDto;
import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import com.nhnacademy.certificate.service.address.AddressService;
import com.nhnacademy.certificate.service.household.HouseholdService;
import com.nhnacademy.certificate.service.householdcompositionresident.HouseholdCompositionResidentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class ResidentRegistrationController {

    private final HouseholdCompositionResidentService hcrService;
    private final AddressService addressService;

    private final HouseholdService householdService;


    public ResidentRegistrationController(HouseholdCompositionResidentService householdCompositionResidentService,
                                          AddressService addressService, HouseholdService householdService) {
        this.hcrService = householdCompositionResidentService;
        this.addressService = addressService;
        this.householdService = householdService;
    }

    @GetMapping("/copy/residents/{serialNumber}")
    public String getResidentRegistration(@PathVariable("serialNumber") Integer householdSerialNumber,
                                          Model model) {

        List<HouseholdMovementAddress> addressList = addressService.findBySerialNumber(householdSerialNumber);
        List<ResidentResponseDto> residentList = hcrService.findResidentsBySerialNumber(householdSerialNumber);
        Household household = householdService.findBySerialNumber(householdSerialNumber);

        model.addAttribute("household", household);
        model.addAttribute("addressList", addressList);
        model.addAttribute("residentList", residentList);
        return "copy/residentRegistration";
    }
}
