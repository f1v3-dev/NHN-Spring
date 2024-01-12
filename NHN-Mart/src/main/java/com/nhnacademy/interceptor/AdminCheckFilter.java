package com.nhnacademy.interceptor;

import com.nhnacademy.domain.Role;
import com.nhnacademy.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminCheckFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user.getRole() != Role.ADMIN) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
