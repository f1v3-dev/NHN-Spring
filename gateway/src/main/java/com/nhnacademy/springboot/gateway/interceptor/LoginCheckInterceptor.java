package com.nhnacademy.springboot.gateway.interceptor;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        boolean present = Optional.ofNullable(request.getSession(true).getAttribute("accountId")).isPresent();
        
        if (present) {
            return true;
        }
        
        response.sendRedirect("/login");
        return false;
    }
}
