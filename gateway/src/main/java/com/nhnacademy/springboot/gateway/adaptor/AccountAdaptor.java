package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterDto;
import java.util.List;

public interface AccountAdaptor {

    List<Account> getAccounts();

    Account getAccount(Long id);

    Account createAccount(AccountRegisterDto account);

    void deleteAccount(Long id);

    boolean matches(AccountLoginRequestDto account);
}
