package com.nhnacdemy.springboot.account.repository;


import com.nhnacdemy.springboot.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
