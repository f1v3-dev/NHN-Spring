package com.nhnacademy.edu.springframework.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.nhnacademy.edu.springframework.aop"})
@EnableAspectJAutoProxy
public class MainConfig {

}
