package com.nhnacademy.certificate.domain;

import java.time.LocalDateTime;


public interface ResidentDto {

    String getName();

    LocalDateTime getBirthDate();

    String getResidentRegistrationNumber();

    String getRegistrationBaseAddress();

    String getGenderCode();
}
