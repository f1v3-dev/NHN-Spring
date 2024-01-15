package com.nhnacademy.shop.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_birth")
    private String birth;

    @Column(name = "user_auth")
    private String auth;

    @Column(name = "user_point")
    private Integer point;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "latest_login_at")
    private LocalDateTime latestLoginAt;


}
