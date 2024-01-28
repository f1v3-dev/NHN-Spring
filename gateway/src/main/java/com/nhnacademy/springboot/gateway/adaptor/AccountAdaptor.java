package com.nhnacademy.springboot.gateway.adaptor;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountDeleteResponse;
import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.CheckAccount;
import com.nhnacademy.springboot.gateway.domain.task.member.UserDto;
import java.util.List;

public interface AccountAdaptor {

    List<Account> getAccounts();

    Account getAccount(Long id);

    Account createAccount(AccountRegisterRequestDto account);

    AccountDeleteResponse deleteAccount(Long accountId);

    Account matches(AccountLoginRequestDto account);

    CheckAccount isAccountExist(UserDto user);
}
