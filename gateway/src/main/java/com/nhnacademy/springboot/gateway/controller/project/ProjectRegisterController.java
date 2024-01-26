package com.nhnacademy.springboot.gateway.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/register")
public class ProjectRegisterController {

    @GetMapping
    public String getProjectRegisterForm() {
        return "project/register";
    }

    @PostMapping
    public String registerProject() {
        return "redirect:/project";
    }
}
