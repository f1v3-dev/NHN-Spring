package com.nhnacdemy.springboot.account.controller;

import com.nhnacdemy.springboot.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AccountWebController {

    private final AccountService accountService;

    @GetMapping("/web/accounts/{id}")
    public String getAccount(@PathVariable("id") Long id, Model model) {

        model.addAttribute("account", accountService.getAccount(id));
        return "account";
    }
}
