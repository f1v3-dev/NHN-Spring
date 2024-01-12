package com.nhnacademy.repository;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;

public interface UserRepository {

    boolean matches(String id, String password);

    void register(String id, String password, String name, Role role);

    User getUser(String id);

    boolean exists(String id);
}