package com.nhnacademy.springboot.gateway.service;

import com.nhnacademy.springboot.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountAdaptor accountAdaptor;

    public AccountService(AccountAdaptor accountAdaptor) {
        this.accountAdaptor = accountAdaptor;
    }

    public List<Account> getAccounts() {
        return accountAdaptor.getAccounts();
    }

    public Account getAccount(Long id) {
        return accountAdaptor.getAccount(id);
    }

    public Account createAccount(AccountRegisterDto account) {
        return accountAdaptor.createAccount(account);
    }

    public void deleteAccount(Long id) {
        accountAdaptor.deleteAccount(id);
    }

    public AccountRequestDto matches(AccountLoginRequestDto account) {
        return accountAdaptor.matches(account);
    }
}
