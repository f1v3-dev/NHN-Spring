package com.nhnacademy.certificate.controller.certificate;

import com.nhnacademy.certificate.domain.IssueDto;
import com.nhnacademy.certificate.domain.ResidentResponseDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.entity.Household;
import com.nhnacademy.certificate.entity.HouseholdMovementAddress;
import com.nhnacademy.certificate.service.address.AddressService;
import com.nhnacademy.certificate.service.certificateissue.CertificateIssueService;
import com.nhnacademy.certificate.service.household.HouseholdService;
import com.nhnacademy.certificate.service.householdcompositionresident.HouseholdCompositionResidentService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/certificate/residents")
public class ResidentRegistrationController {

    private final HouseholdCompositionResidentService hcrService;
    private final AddressService addressService;
    private final HouseholdService householdService;
    private final CertificateIssueService certificateIssueService;


    public ResidentRegistrationController(HouseholdCompositionResidentService householdCompositionResidentService,
                                          AddressService addressService, HouseholdService householdService,
                                          CertificateIssueService certificateIssueService) {
        this.hcrService = householdCompositionResidentService;
        this.addressService = addressService;
        this.householdService = householdService;
        this.certificateIssueService = certificateIssueService;
    }

    @GetMapping("/{householdSerialNumber}")
    public String getResidentRegistration(@PathVariable Integer householdSerialNumber,
                                          @RequestParam Long certificateConfirmationNumber,
                                          Model model) {

        List<HouseholdMovementAddress> addressList = addressService.findBySerialNumber(householdSerialNumber);
        List<ResidentResponseDto> residentList = hcrService.findResidentsBySerialNumber(householdSerialNumber);
        Household household = householdService.findBySerialNumber(householdSerialNumber);
        IssueDto issue = certificateIssueService.findById(certificateConfirmationNumber);

        model.addAttribute("household", household);
        model.addAttribute("addressList", addressList);
        model.addAttribute("residentList", residentList);
        model.addAttribute("issue", issue);
        return "certificate/residentRegistration";
    }

    @PostMapping
    public String registerResidentRegistration(@RequestParam Integer householdSerialNumber,
                                           @RequestParam Integer residentSerialNumber) {

        CertificateIssue register = certificateIssueService.register(residentSerialNumber, "주민등록등본");

        return "redirect:/certificate/residents/" + householdSerialNumber + "?certificateConfirmationNumber=" +
                register.getCertificateConfirmationNumber();
    }
}
