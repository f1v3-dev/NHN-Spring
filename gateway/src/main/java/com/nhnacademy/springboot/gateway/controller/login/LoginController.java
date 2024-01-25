package com.nhnacademy.springboot.gateway.controller.login;

import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.AccountService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getLoginForm(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);

        if (Optional.ofNullable(session.getAttribute("accountId")).isPresent()) {
            response.sendRedirect("/");
        }

        return "auth/login";
    }

    @PostMapping
    public String doLogin(@Valid AccountLoginRequestDto account,
                          BindingResult bindingResult,
                          HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException();
        }

        HttpSession session = request.getSession(true);

//        if (accountService.matches(account)) {
//            session.setAttribute("accountId", account.getId());
//            return "redirect:/";
//        }

        if ("1234".equals(account.getId()) && "1234".equals(account.getPassword())) {
            session.setAttribute("accountId", account.getId());
            return "redirect:/";
        }

        return "redirect:/login";
    }
}
