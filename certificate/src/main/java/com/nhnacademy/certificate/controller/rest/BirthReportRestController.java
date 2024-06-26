package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.rest.BirthDeathRequestDto;
import com.nhnacademy.certificate.service.birthdeath.BirthDeathReportResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
public class BirthReportRestController {
    private final String typeCode = "출생";

    private final BirthDeathReportResidentService birthReportService;


    public BirthReportRestController(BirthDeathReportResidentService birthReportService) {
        this.birthReportService = birthReportService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerBirthReport(@RequestBody BirthDeathRequestDto birthReport,
                                    @PathVariable("serialNumber")
                                    Integer reportSerialNumber) {

        birthReportService.register(reportSerialNumber, birthReport, typeCode);
    }

    @PutMapping("/{targetSerialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyBirthReport(@RequestBody BirthDeathRequestDto birthReport,
                                  @PathVariable("serialNumber")
                                  Integer reportSerialNumber,
                                  @PathVariable("targetSerialNumber")
                                  Integer targetSerialNumber) {

        birthReport.setTargetSerialNumber(targetSerialNumber);
        birthReportService.modify(reportSerialNumber, birthReport, typeCode);

    }

    @DeleteMapping("/{targetSerialNumber}")
    public ResponseEntity<BirthDeathRequestDto> deleteBirthReport(
            @PathVariable("serialNumber") Integer reportSerialNumber,
            @PathVariable("targetSerialNumber") Integer targetSerialNumber) {

        birthReportService.delete(reportSerialNumber, targetSerialNumber, typeCode);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
