package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRequestDto;
import java.util.List;

public interface AccountAdaptor {

    List<Account> getAccounts();

    Account getAccount(Long id);

    Account createAccount(AccountRegisterDto account);

    void deleteAccount(Long id);

    AccountRequestDto matches(AccountLoginRequestDto account);
}
