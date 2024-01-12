package com.nhnacademy.repository;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import com.nhnacademy.exception.UserAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public boolean matches(String id, String password) {

        return Optional.ofNullable(getUser(id))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public void register(String id, String password, String name, Role role) {

        if (exists(id)) {
            throw new UserAlreadyExistsException();
        }

        User user = new User(id, password, name, role);
        userMap.put(id, user);
    }

    @Override
    public User getUser(String id) {

        return exists(id) ? userMap.get(id) : null;
    }

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }
}
