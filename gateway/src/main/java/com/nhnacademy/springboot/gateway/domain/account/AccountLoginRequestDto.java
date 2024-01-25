package com.nhnacademy.springboot.gateway.domain.account;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AccountLoginRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
}
