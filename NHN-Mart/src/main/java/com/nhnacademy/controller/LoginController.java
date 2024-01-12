package com.nhnacademy.controller;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import com.nhnacademy.domain.UserLoginRequest;
import com.nhnacademy.exception.UserNotFoundException;
import com.nhnacademy.exception.ValidationFailedException;
import com.nhnacademy.service.UserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
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

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loginForm(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
            return "loginForm";
        }

        User user = (User) session.getAttribute("user");
        if (user.getRole() == Role.CUSTOMER) {
            return "customer/home";
        }

        return "redirect:/";
    }


    @PostMapping
    public String doLogin(@Valid UserLoginRequest userRequest,
                          BindingResult bindingResult,
                          HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        String id = userRequest.getId();
        String password = userRequest.getPassword();
        User user = userService.doLogin(id, password);

        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        return "redirect:/";
    }

}
