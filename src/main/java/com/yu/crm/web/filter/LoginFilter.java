package com.yu.crm.web.filter;

import com.yu.crm.settings.domain.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 *
 */
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入登录验证过滤器");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getServletPath();

        //不应该拦截的资源，自动放行请求
        if ("/login.jsp".equals(path)  || "/settings/user/login.do".equals(path)){
            chain.doFilter(request,response);

        //其他资源必须验证有没有登陆过
        }else{

            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");

            //如果user不等于空，说明登录过
            if(user != null){
                chain.doFilter(request,response);
                //没有登陆过，重定向到登录页面
            }else{
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }
}
