package com.nhnacademy.certificate.domain;

import java.time.LocalDateTime;

public interface ResidentBirthDto {

    String getName();

    String getGenderCode();

    LocalDateTime getBirthDate();

    String getRegistrationBaseAddress();

    String getBirthPlaceCode();
}
