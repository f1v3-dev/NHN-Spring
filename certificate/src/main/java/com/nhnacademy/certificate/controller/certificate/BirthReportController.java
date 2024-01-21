package com.nhnacademy.certificate.controller.certificate;

import com.nhnacademy.certificate.domain.BirthReportResidentResponseDto;
import com.nhnacademy.certificate.domain.ParentResponseDto;
import com.nhnacademy.certificate.domain.ResidentBirthDto;
import com.nhnacademy.certificate.service.birthdeath.BirthDeathReportResidentService;
import com.nhnacademy.certificate.service.certificateissue.CertificateIssueService;
import com.nhnacademy.certificate.service.family.FamilyRelationshipService;
import com.nhnacademy.certificate.service.resident.ResidentService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/certificate/birth")
public class BirthReportController {

    private final ResidentService residentService;

    private final BirthDeathReportResidentService birthDeathReportResidentService;

    private final FamilyRelationshipService familyRelationshipService;

    private final CertificateIssueService certificateIssueService;

    public BirthReportController(ResidentService residentService,
                                 BirthDeathReportResidentService birthDeathReportResidentService,
                                 FamilyRelationshipService familyRelationshipService,
                                 CertificateIssueService certificateIssueService) {
        this.residentService = residentService;
        this.birthDeathReportResidentService = birthDeathReportResidentService;
        this.familyRelationshipService = familyRelationshipService;
        this.certificateIssueService = certificateIssueService;
    }

    @GetMapping("/{residentSerialNumber}")
    public String getBirthReport(@PathVariable Integer residentSerialNumber,
                                 Model model) {
        ResidentBirthDto birthResident = residentService.findBirthResident(residentSerialNumber);
        BirthReportResidentResponseDto reportResident =
                birthDeathReportResidentService.findBirthReportResident(residentSerialNumber);
        List<ParentResponseDto> parents = familyRelationshipService.findParents(residentSerialNumber);


        model.addAttribute("birthResident", birthResident);
        model.addAttribute("reportResident", reportResident);
        model.addAttribute("parents", parents);

        return "certificate/birthReport";
    }

    @PostMapping
    public String registerBirthReport(@RequestParam Integer residentSerialNumber) {
        certificateIssueService.register(residentSerialNumber, "출생신고서");

        return "redirect:/certificate/birth/" + residentSerialNumber;
    }

}
