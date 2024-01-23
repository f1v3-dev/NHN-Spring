package com.nhnacdemy.springboot.openapi;

import com.nhnacdemy.springboot.openapi.domain.Account;
import com.nhnacdemy.springboot.openapi.service.AccountClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class OpenapiApplication {

    private final AccountClientService accountClientService;

    public OpenapiApplication(AccountClientService accountClientService) {
        this.accountClientService = accountClientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(OpenapiApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener() {
        return event -> accountClientService.getAccounts()
                .forEach(account -> log.info("multi = {}", account));
    }
}
