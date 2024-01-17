package com.nhnacademy.shop.repository;

import com.nhnacademy.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
