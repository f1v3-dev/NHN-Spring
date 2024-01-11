package com.nhnacademy.domain;

// 아이디, 비밀번호, 이름 등의 속성이 존재.


import lombok.Value;

@Value
public class User {

    String id;

    String password;

    String name;

    Role role;

}
