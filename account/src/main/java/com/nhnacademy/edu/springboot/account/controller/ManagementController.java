package com.nhnacademy.edu.springboot.account.controller;

import com.nhnacademy.edu.springboot.account.management.MyHealthIndicator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {

    private final MyHealthIndicator indicator;

    public ManagementController(MyHealthIndicator indicator) {
        this.indicator = indicator;
    }

    @PostMapping("/management/fail")
    public String managementFail() {
        indicator.setHealth(false);

        return "health has set as fail";

    }
}
