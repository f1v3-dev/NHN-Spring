package com.nhnacademy.certificate.controller.certificate;

import com.nhnacademy.certificate.domain.DeathReportResidentResponseDto;
import com.nhnacademy.certificate.domain.ResidentDeathDto;
import com.nhnacademy.certificate.service.birthdeath.BirthDeathReportResidentService;
import com.nhnacademy.certificate.service.resident.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/certificate/death")
public class DeathReportController {

    private final ResidentService residentService;

    private final BirthDeathReportResidentService birthDeathReportResidentService;

    public DeathReportController(ResidentService residentService,
                                 BirthDeathReportResidentService birthDeathReportResidentService) {
        this.residentService = residentService;
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @GetMapping
    public String getDeathReport(@RequestParam("serialNumber") Integer residentSerialNumber,
                                 Model model) {

        ResidentDeathDto deathResident = residentService.findDeathResident(residentSerialNumber);
        DeathReportResidentResponseDto reportResident =
                birthDeathReportResidentService.findDeathReportResident(residentSerialNumber);

        model.addAttribute("deathResident", deathResident);
        model.addAttribute("reportResident", reportResident);

        return "certificate/deathReport";
    }
}
