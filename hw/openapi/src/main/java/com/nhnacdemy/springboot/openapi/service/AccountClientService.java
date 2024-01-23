package com.nhnacdemy.springboot.openapi.service;

import com.nhnacdemy.springboot.openapi.domain.Account;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountClientService {

    private final AccountAdaptor accountAdaptor;

    public AccountClientService(AccountAdaptor accountAdaptor) {
        this.accountAdaptor = accountAdaptor;
    }

    public List<Account> getAccounts() {
        return accountAdaptor.getAccounts();
    }

    public Account getAccount(Long id) {
        return accountAdaptor.getAccount(id);
    }

    public void createAccount(Account account) {
        accountAdaptor.createAccount(account);
    }

    public void deleteAccount(Long id) {
        accountAdaptor.deleteAccount(id);
    }
}
