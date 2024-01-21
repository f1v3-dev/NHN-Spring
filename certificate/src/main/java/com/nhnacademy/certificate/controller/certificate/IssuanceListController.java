package com.nhnacademy.certificate.controller.certificate;

import com.nhnacademy.certificate.domain.IssueListDto;
import com.nhnacademy.certificate.service.certificateissue.CertificateIssueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/certificate/issuanceList")
public class IssuanceListController {

    private final CertificateIssueService certificateIssueService;

    public IssuanceListController(CertificateIssueService certificateIssueService) {
        this.certificateIssueService = certificateIssueService;
    }

    @GetMapping("/{serialNumber}")
    public String getIssuanceList(@PathVariable Integer serialNumber,
                                  @PageableDefault(size = 5) Pageable pageable,
                                  Model model) {

        Page<IssueListDto> issueList = certificateIssueService.findBySerialNumber(serialNumber, pageable);

        model.addAttribute("serialNumber", serialNumber);
        model.addAttribute("issueList", issueList.getContent());
        model.addAttribute("totalPages", issueList.getTotalPages());

        return "certificate/issuanceList";
    }
}
