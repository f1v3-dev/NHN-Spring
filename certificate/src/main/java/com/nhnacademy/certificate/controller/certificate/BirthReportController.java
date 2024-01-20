package com.nhnacademy.certificate.controller.certificate;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.domain.ParentResponseDto;
import com.nhnacademy.certificate.domain.ResidentBirthDto;
import com.nhnacademy.certificate.service.birthdeath.BirthDeathReportResidentService;
import com.nhnacademy.certificate.service.family.FamilyRelationshipService;
import com.nhnacademy.certificate.service.resident.ResidentService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/certificate/birth")
public class BirthReportController {

    private final ResidentService residentService;

    private final BirthDeathReportResidentService birthDeathReportResidentService;

    private final FamilyRelationshipService familyRelationshipService;

    public BirthReportController(ResidentService residentService,
                                 BirthDeathReportResidentService birthDeathReportResidentService,
                                 FamilyRelationshipService familyRelationshipService) {
        this.residentService = residentService;
        this.birthDeathReportResidentService = birthDeathReportResidentService;
        this.familyRelationshipService = familyRelationshipService;
    }

    @GetMapping
    public String getBirthReport(@RequestParam("serialNumber") Integer residentSerialNumber,
                                 Model model) {
        ResidentBirthDto birthResident = residentService.findBirthResident(residentSerialNumber);
        BirthReportResidentResponseDto reportResident =
                birthDeathReportResidentService.findReportResident(residentSerialNumber);
        List<ParentResponseDto> parents = familyRelationshipService.findParents(residentSerialNumber);

        System.out.println("birthResident.getBirthDate = " + birthResident.getBirthDate());
        System.out.println("reportResident = " + reportResident);
        System.out.println("parents = " + parents);

        model.addAttribute("birthResident", birthResident);
        model.addAttribute("reportResident", reportResident);

        model.addAttribute("parents", parents);

        return "certificate/birthReport";

    }

}
