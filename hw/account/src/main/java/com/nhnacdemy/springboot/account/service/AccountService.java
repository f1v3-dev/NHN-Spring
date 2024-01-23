package com.nhnacdemy.springboot.account.service;


import com.nhnacdemy.springboot.account.entity.Account;
import java.util.List;

public interface AccountService {

    List<Account> getAccounts();

    Account getAccount(Long id);

    Account createAccount(Account account);

    void deleteAccount(Long id);
}
