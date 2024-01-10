package com.nhnacademy.springmvc.controller;

import com.nhnacademy.springmvc.repository.UserRepository;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String loginForm(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        if (Objects.isNull(session) || Objects.isNull(session.getAttribute("id"))) {
            return "loginForm";
        }

        model.addAttribute("id", session.getAttribute("id"));
        return "loginSuccess";
    }

    @PostMapping
    public String doLogin(@RequestParam String id,
                          @RequestParam String pwd,
                          HttpServletRequest request,
                          Model model) {

        if (userRepository.matches(id, pwd)) {
            HttpSession session = request.getSession(true);

            session.setAttribute("id", id);
            session.setMaxInactiveInterval(60 * 3);
            model.addAttribute("id", id);
            return "loginSuccess";
        }

        return "redirect:/login";
    }

}
