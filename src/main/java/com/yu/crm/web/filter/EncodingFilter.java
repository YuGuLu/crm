package com.yu.crm.web.filter;

import jakarta.servlet.*;

import java.io.IOException;

/**
 *
 */
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入中文乱码过滤器");

        //过滤post请求中文乱码问题
        req.setCharacterEncoding("UTF-8");
        //过滤响应流响应中文乱码问题
        resp.setContentType("text/html;charset=utf-8");

        //将请求放行
        chain.doFilter(req,resp);
    }
}
