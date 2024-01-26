package com.nhnacademy.springboot.gateway.controller.login;

import com.nhnacademy.springboot.gateway.domain.account.AccountDeleteResponse;
import com.nhnacademy.springboot.gateway.service.AccountService;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WithdrawalController {

    private final AccountService accountService;

    public WithdrawalController(AccountService accountService) {
        this.accountService = accountService;
    }

    @DeleteMapping("/account/{accountId}")
    public String deleteAccount(@PathVariable("accountId") Long accountId,
                                HttpServletRequest request) {

        AccountDeleteResponse response = accountService.deleteAccount(accountId);

        if ("OK".equals(response.getResult())) {
            Optional.ofNullable(request.getSession())
                    .ifPresent(HttpSession::invalidate);

            return "redirect:/login";
        }

        return "redirect:/mypage";
    }
}
