package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.rest.ResidentRequestDto;
import com.nhnacademy.certificate.service.resident.ResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResidentRestController {

    private final ResidentService residentService;

    public ResidentRestController(ResidentService residentService) {
        this.residentService = residentService;

    }

    @PostMapping("/residents")
    public ResponseEntity<ResidentRequestDto> registerResident(@RequestBody ResidentRequestDto resident) {

        residentService.register(resident);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/residents/{serialNumber}")
    public ResponseEntity<ResidentRequestDto> modifyResident(@PathVariable Integer serialNumber,
                                            @RequestBody ResidentRequestDto resident) {

        resident.setResidentSerialNumber(serialNumber);

        residentService.modify(serialNumber, resident);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
