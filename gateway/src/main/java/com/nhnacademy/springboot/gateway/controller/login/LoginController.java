package com.nhnacademy.springboot.gateway.controller.login;

import com.nhnacademy.springboot.gateway.domain.account.AccountLoginRequestDto;
import com.nhnacademy.springboot.gateway.domain.account.Account;
import com.nhnacademy.springboot.gateway.domain.task.TaskUser;
import com.nhnacademy.springboot.gateway.exception.ValidationFailedException;
import com.nhnacademy.springboot.gateway.service.AccountService;
import com.nhnacademy.springboot.gateway.service.TaskService;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private final AccountService accountService;

    private final TaskService taskService;

    public LoginController(AccountService accountService, TaskService taskService) {
        this.accountService = accountService;
        this.taskService = taskService;
    }

    @GetMapping
    public String getLoginForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);

        if (Optional.ofNullable(session.getAttribute("account")).isPresent()) {
            response.sendRedirect("/");
        }

        return "auth/login";
    }

    @PostMapping
    public String doLogin(@Valid AccountLoginRequestDto account,
                          BindingResult bindingResult,
                          HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        HttpSession session = request.getSession(true);

        log.info("account = {}", account);

        Account loginAccount = accountService.matches(account);
        if (Objects.nonNull(loginAccount)) {

//            TaskUser taskUser = taskService.matches(loginAccount.getUserId());
//            session.setAttribute("taskUser", taskUser);
            session.setAttribute("account", loginAccount);
            log.info("account = {}", loginAccount);
            return "redirect:/";
        }

        return "redirect:/login";
    }
}
