package com.prestu.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 范成恒
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = "username";
        System.out.println("-----进入拦截器------");
        System.out.println(request.getSession().getAttribute(username));
        if (request.getSession().getAttribute(username)==null) {
            request.setAttribute("msg","不好意思，您未登录，请您先登录");
            request.getRequestDispatcher("/user/login").forward(request,response);
            System.out.println("拦截器，登录成功");
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
