package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.rest.BirthRequestDto;
import com.nhnacademy.certificate.service.birthdeath.BirthDeathReportResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
public class BirthReportRestController {

    private final BirthDeathReportResidentService birthReportService;

    public BirthReportRestController(BirthDeathReportResidentService birthReportService) {
        this.birthReportService = birthReportService;
    }

    @PostMapping
    public ResponseEntity<?> registerBirthReport(@RequestBody BirthRequestDto birthReport,
                                                 @PathVariable("serialNumber") Integer reportSerialNumber) {

        birthReportService.registerBirth(
                reportSerialNumber,
                birthReport);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{targetSerialNumber}")
    public ResponseEntity<?> modifyBirthReport(@RequestBody BirthRequestDto birthReport,
                                               @PathVariable("serialNumber") Integer reportSerialNumber,
                                               @PathVariable("targetSerialNumber") Integer targetSerialNumber) {

        birthReport.setTargetSerialNumber(targetSerialNumber);
        birthReportService.modify(
                reportSerialNumber,
                birthReport);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{targetSerialNumber}")
    public ResponseEntity<?> deleteBirthReport(@PathVariable("serialNumber") Integer reportSerialNumber,
                                               @PathVariable("targetSerialNumber") Integer targetSerialNumber) {

        birthReportService.deleteBirthReport(reportSerialNumber, targetSerialNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
