package com.nhnacademy.service;

import com.nhnacademy.domain.User;

public interface UserService {

    User doLogin(String id, String password);

    boolean isMatch(String id, String password);

    User getUser(String id);
}
