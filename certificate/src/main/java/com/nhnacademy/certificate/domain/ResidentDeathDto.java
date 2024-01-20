package com.nhnacademy.certificate.domain;

import java.time.LocalDateTime;

public interface ResidentDeathDto {

    String getName();

    String getResidentRegistrationNumber();

    LocalDateTime getDeathDate();

    String getDeathPlaceCode();

    String getDeathPlaceAddress();

}
