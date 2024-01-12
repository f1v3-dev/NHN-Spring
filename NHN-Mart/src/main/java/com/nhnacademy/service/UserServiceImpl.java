package com.nhnacademy.service;

import com.nhnacademy.domain.User;
import com.nhnacademy.exception.UserNotFoundException;
import com.nhnacademy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User doLogin(String id, String password) {
        if (isMatch(id, password)) {
            return getUser(id);
        }

        throw new UserNotFoundException();
    }

    @Override
    public boolean isMatch(String id, String password) {
        return userRepository.matches(id, password);
    }

    @Override
    public User getUser(String id) {
        return userRepository.getUser(id);
    }
}
