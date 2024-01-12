package com.nhnacademy.controller;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {

        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("user"))) {
            model.addAttribute("user", session.getAttribute("user"));
        }

        return "index";
    }
}
