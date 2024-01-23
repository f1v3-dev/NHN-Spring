package com.nhnacdemy.springboot.openapi.config;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.nhnacademy.account")
@Data
public class AccountAdaptorProperties {

    @NotNull
    private String address;
}
