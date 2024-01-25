package com.nhnacademy.springboot.gateway.controller.user;

import com.nhnacademy.springboot.gateway.domain.account.AccountRegisterDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.AccountStatus;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.AccountService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


        AccountRequestDto account = (AccountRequestDto) request.getSession(true).getAttribute("account");
        model.addAttribute("account", account);
        model.addAttribute("statuses", AccountStatus.values());

        return "user/mypage";
    }

    @PostMapping
    public String modifyMyPage(@Valid AccountRegisterDto account, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return null;

    }
}
