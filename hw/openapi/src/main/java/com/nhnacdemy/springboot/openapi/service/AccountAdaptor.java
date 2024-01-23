package com.nhnacdemy.springboot.openapi.service;

import com.nhnacdemy.springboot.openapi.domain.Account;
import java.util.List;

public interface AccountAdaptor {

    List<Account> getAccounts();

    Account getAccount(Long id);

    void createAccount(Account account);

    void deleteAccount(Long id);
}
