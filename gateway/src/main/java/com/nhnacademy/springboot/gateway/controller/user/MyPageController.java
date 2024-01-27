package com.nhnacademy.springboot.gateway.controller.user;

import com.nhnacademy.springboot.gateway.domain.account.Account;
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

    @GetMapping
    public String getMyPage(HttpServletRequest request, Model model) {


        Account account = (Account) request.getSession(true).getAttribute("account");
        model.addAttribute("account", account);

        log.info("account = {}", account);

        return "user/my-page";
    }

}
