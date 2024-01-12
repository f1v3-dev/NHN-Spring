package com.nhnacademy.domain;

import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserLoginRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    String password;
}
