package com.nhnacademy.springboot.gateway.domain.account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AccountRegisterDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotNull
    private String phoneNumber;
}
