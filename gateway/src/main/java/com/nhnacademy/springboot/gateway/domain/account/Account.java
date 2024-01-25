package com.nhnacademy.springboot.gateway.domain.account;

import lombok.Data;

@Data
public class Account {

    /**
     * Long - ID??
     * String ID
     * String password
     * String email
     */

    private String id;

    private String email;

    private String password;
}
