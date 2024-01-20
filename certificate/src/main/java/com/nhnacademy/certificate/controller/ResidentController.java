package com.nhnacademy.certificate.controller;

import com.nhnacademy.certificate.domain.ResidentListDto;
import com.nhnacademy.certificate.service.resident.ResidentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService residentService;


    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }


    @GetMapping
    public String index(@PageableDefault(size = 3) Pageable pageable, Model model) {

        Page<ResidentListDto> residentPage = residentService.findList(pageable);

        model.addAttribute("residentList", residentPage.getContent());
        model.addAttribute("totalPages", residentPage.getTotalPages());

        return "resident/index";
    }

}
