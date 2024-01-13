package com.nhnacademy.domain;


import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class User {

    @NotBlank
    String id;

    @NotBlank
    String password;

    @NotBlank
    String name;

    Role role;

}
