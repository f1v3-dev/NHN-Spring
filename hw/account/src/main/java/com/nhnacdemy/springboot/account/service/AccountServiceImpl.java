package com.nhnacdemy.springboot.account.service;

import com.nhnacdemy.springboot.account.entity.Account;
import com.nhnacdemy.springboot.account.repository.AccountRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {

        boolean present = accountRepository.findById(account.getId()).isPresent();
        if (present) {
            throw new IllegalStateException();
        }

        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
