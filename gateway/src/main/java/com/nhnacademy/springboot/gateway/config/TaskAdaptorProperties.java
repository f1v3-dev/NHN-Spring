package com.nhnacademy.springboot.gateway.config;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.nhnacademy.task")
@Data
public class TaskAdaptorProperties {

    @NotNull
    private String address;
}
