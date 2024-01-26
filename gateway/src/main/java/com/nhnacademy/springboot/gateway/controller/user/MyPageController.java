package com.nhnacademy.springboot.gateway.controller.user;

import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.account.AccountStatus;
import com.nhnacademy.springboot.gateway.service.AccountService;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class MyPageController {

    // session에 있는 값을 통해서 접근하면 될듯
    // session.getAttribute("accountId");

    private final AccountService accountService;

    public MyPageController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getMyPage(HttpServletRequest request, Model model) {


        Account account = (Account) request.getSession(true).getAttribute("account");
        model.addAttribute("account", account);
        model.addAttribute("statuses", AccountStatus.values());

        log.info("account = {}", account);

        return "user/my-page";
    }

}
