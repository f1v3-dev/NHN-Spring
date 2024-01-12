package com.nhnacademy.controller;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import com.nhnacademy.domain.UserLoginRequest;
import com.nhnacademy.exception.UserNotFoundException;
import com.nhnacademy.exception.ValidationFailedException;
import com.nhnacademy.repository.UserRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        if (userRepository.matches(userRequest.getId(), userRequest.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", userRepository.getUser(userRequest.getId()));
            session.setMaxInactiveInterval(60 * 3);

            return "redirect:/";
        }

        throw new UserNotFoundException();
    }

}
