package com.nhnacdemy.springboot.openapi.controller;

import com.nhnacdemy.springboot.openapi.domain.Account;
import com.nhnacdemy.springboot.openapi.service.AccountClientService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/accounts")
public class AccountRestController {

    private final AccountClientService accountService;

    public AccountRestController(AccountClientService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@PathVariable("id") Long id) {
        return accountService.getAccount(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
    }

    @DeleteMapping("{id}")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
    }
}