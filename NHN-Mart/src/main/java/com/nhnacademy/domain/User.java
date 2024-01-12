package com.nhnacademy.domain;

// 아이디, 비밀번호, 이름 등의 속성이 존재.


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
