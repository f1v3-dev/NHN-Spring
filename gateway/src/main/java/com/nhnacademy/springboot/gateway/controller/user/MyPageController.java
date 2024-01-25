package com.nhnacademy.springboot.gateway.controller.user;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    // session에 있는 값을 통해서 접근하면 될듯
    // session.getAttribute("accountId");

    @GetMapping
    public String getMyPage(HttpServletRequest request,
                            Model model) {

        Long id = (Long) request.getAttribute("accountId");


        return null;

    }

}
