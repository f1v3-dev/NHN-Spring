package com.nhnacademy.springboot.gateway.domain.account;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class AccountRequestDto implements Serializable {
    // session에 넣기 위해서 Serializable 사용!!

    private Long id;

    private String userId;

    private String password;

    private String name;

    private String email;

    private String phoneNumber;

    private AccountStatus status;
}
