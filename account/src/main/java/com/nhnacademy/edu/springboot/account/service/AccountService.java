package com.nhnacademy.edu.springboot.account.service;

import com.nhnacademy.edu.springboot.account.domain.Account;
import java.util.List;

public interface AccountService {

    List<Account> getAccounts();

    Account getAccount(Long id);

    Account createAccount(Account account);

    void deleteAccount(Long id);
}
