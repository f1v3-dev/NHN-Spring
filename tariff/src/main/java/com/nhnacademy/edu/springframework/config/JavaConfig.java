package com.nhnacademy.edu.springframework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:path.properties")
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework")
@EnableAspectJAutoProxy
public class JavaConfig {
}
