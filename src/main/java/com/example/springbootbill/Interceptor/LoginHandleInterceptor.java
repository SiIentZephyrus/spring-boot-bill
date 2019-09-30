package com.example.springbootbill.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser != null){
            return true;
        }
        request.setAttribute("msg","您还没有登录，请先去登录");
        request.getRequestDispatcher("/index.html").forward(request,response);
        return false;
    }

}
