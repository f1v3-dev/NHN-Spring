package com.nhnacademy.config;

import com.nhnacademy.Base;
import com.nhnacademy.domain.Role;
import com.nhnacademy.repository.UserRepository;
import com.nhnacademy.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = @ComponentScan.Filter(Controller.class))
@Configuration
public class RootConfig {

    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.register("customer", "12345", "고객", Role.CUSTOMER);
        userRepository.register("admin", "12345", "관리자", Role.ADMIN);

        return userRepository;
    }

}
