package com.nhnacademy.springboot.gateway.domain.account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AccountRegisterRequestDto {
    /**
     *     private String name;
     *     private String userId;
     *     private String password;
     *     private String email;
     *     private String phoneNumber;
     */

    @NotBlank
    private String name;

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @Email
    private String email;

    @NotNull
    private String phoneNumber;
}
