package com.nhnacademy.edu.springboot.account.controller;

import com.nhnacademy.edu.springboot.account.config.VersionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class VersionController {

    private final VersionProperties versionProperties;

    public VersionController(VersionProperties versionProperties) {
        this.versionProperties = versionProperties;
    }


    @GetMapping("/system/version")
    public ResponseEntity<VersionProperties> getVersion() {

        log.debug("versionProperties = {}", versionProperties.getVersion());

        return ResponseEntity.ok().body(versionProperties);
    }
}
