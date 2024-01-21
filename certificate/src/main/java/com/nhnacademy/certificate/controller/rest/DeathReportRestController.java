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
@RequestMapping("/residents/{serialNumber}/death")
public class DeathReportRestController {

    private final String typeCode = "사망";

    private final BirthDeathReportResidentService deathReportService;


    public DeathReportRestController(BirthDeathReportResidentService deathReportService) {
        this.deathReportService = deathReportService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerDeathReport(@RequestBody BirthDeathRequestDto deathReport,
                                    @PathVariable("serialNumber") Integer reportSerialNumber) {

        deathReportService.register(reportSerialNumber, deathReport, typeCode);
    }

    @PutMapping("/{targetSerialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyDeathReport(@RequestBody BirthDeathRequestDto deathReport,
                                  @PathVariable("serialNumber")
                                  Integer reportSerialNumber,
                                  @PathVariable("targetSerialNumber")
                                  Integer targetSerialNumber) {

        deathReport.setTargetSerialNumber(targetSerialNumber);
        deathReportService.modify(reportSerialNumber, deathReport, typeCode);

    }

    @DeleteMapping("/{targetSerialNumber}")
    public ResponseEntity<BirthDeathRequestDto> deleteDeathReport(
            @PathVariable("serialNumber") Integer reportSerialNumber,
            @PathVariable("targetSerialNumber") Integer targetSerialNumber) {

        deathReportService.delete(reportSerialNumber, targetSerialNumber, typeCode);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
