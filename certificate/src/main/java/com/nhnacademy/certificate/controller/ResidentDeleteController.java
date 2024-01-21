package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.service.resident.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/residents/delete")
public class ResidentDeleteController {

    private final ResidentService residentService;

    public ResidentDeleteController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @DeleteMapping
    public String deleteResident(@RequestParam Integer residentSerialNumber) {

        residentService.deleteResident(residentSerialNumber);

        return "redirect:/residents";
    }
}
