package com.nhnacademy.springboot.gateway.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckAccount {
    private boolean exist;
}
