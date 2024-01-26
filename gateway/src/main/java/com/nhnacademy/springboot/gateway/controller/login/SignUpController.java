package com.nhnacademy.springboot.gateway.controller.login;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterDto;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.AccountService;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final AccountService accountService;

    public SignUpController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getSignUpForm() {
        return "auth/signup";
    }

    @PostMapping
    public String singUp(@Valid AccountRegisterDto account,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        log.info("account = {}", account);

        Account signupAccount = accountService.createAccount(account);

        if (Objects.isNull(signupAccount)) {
            return "redirect:/signup";
        }

        return "redirect:/login";
    }
}
