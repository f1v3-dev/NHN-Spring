package com.nhnacademy.edu.springframework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.nhnacademy.edu.springframework.config"})
@PropertySource("classpath:/message.properties")
public class MainConfig {

}
