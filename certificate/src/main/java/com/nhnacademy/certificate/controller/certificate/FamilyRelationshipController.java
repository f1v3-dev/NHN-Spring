package com.nhnacademy.certificate.controller.certificate;

import com.nhnacademy.certificate.domain.IssueDto;
import com.nhnacademy.certificate.domain.ResidentDto;
import com.nhnacademy.certificate.domain.ResidentFamilyDto;
import com.nhnacademy.certificate.entity.CertificateIssue;
import com.nhnacademy.certificate.service.certificateissue.CertificateIssueService;
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
@RequestMapping("/certificate/family")
public class FamilyRelationshipController {

    private final CertificateIssueService certificateIssueService;
    private final ResidentService residentService;

    public FamilyRelationshipController(CertificateIssueService certificateIssueService,
                                        ResidentService residentService) {
        this.certificateIssueService = certificateIssueService;
        this.residentService = residentService;
    }

    @GetMapping("/{residentSerialNumber}")
    public String getFamilyRelationship(@PathVariable Integer residentSerialNumber,
                                        @RequestParam Long certificateConfirmationNumber,
                                        Model model) {

        IssueDto issue = certificateIssueService.findById(certificateConfirmationNumber);
        ResidentDto self = residentService.findById(residentSerialNumber);
        List<ResidentFamilyDto> family = residentService.findFamilyById(residentSerialNumber);

        model.addAttribute("issue", issue);
        model.addAttribute("self", self);
        model.addAttribute("family", family);

        return "certificate/familyRelationship";
    }


    @PostMapping
    public String registerFamilyRelationship(@RequestParam Integer residentSerialNumber) {


        CertificateIssue register = certificateIssueService.register(residentSerialNumber, "가족관계증명서");

        return "redirect:/certificate/family/" + residentSerialNumber + "?certificateConfirmationNumber=" +
                register.getCertificateConfirmationNumber();
    }
}
