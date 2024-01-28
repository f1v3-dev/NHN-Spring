package com.nhnacademy.springboot.gateway.service;

import com.nhnacademy.springboot.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountDeleteResponse;
import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.CheckAccount;
import com.nhnacademy.springboot.gateway.domain.task.member.UserDto;
import java.util.List;
import javax.validation.Valid;
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

    public Account createAccount(AccountRegisterRequestDto account) {
        return accountAdaptor.createAccount(account);
    }

    public AccountDeleteResponse deleteAccount(Long accountId) {
        return accountAdaptor.deleteAccount(accountId);
    }

    public Account matches(AccountLoginRequestDto account) {
        return accountAdaptor.matches(account);
    }

    public CheckAccount isAccountExist(UserDto user) {
        return accountAdaptor.isAccountExist(user);
    }
}
