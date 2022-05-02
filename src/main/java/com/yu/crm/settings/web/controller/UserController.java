package com.yu.crm.settings.web.controller;

import com.yu.crm.settings.domain.User;
import com.yu.crm.settings.service.UserService;
import com.yu.crm.settings.service.impl.UserServiceImpl;
import com.yu.crm.utils.MD5Util;
import com.yu.crm.utils.PrintJson;
import com.yu.crm.utils.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户登录控制器");

        String path = request.getServletPath();
        if("/settings/user/login.do".equals(path)){

            login(request,response);

        }else if("/settings/user/xx.do".equals(path)){

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到验证登录");

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        //将密码的明文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接收浏览器端的ip地址
        String ip = request.getRemoteAddr();
        System.out.println(ip);

        //未来业务层开发，统一使用代理类形态的接口对象
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try{
            User user = us.login(loginAct,loginPwd,ip);
            request.getSession().setAttribute("user",user);

            //如果程序执行到此处，说明业务层没有为控制器抛出任何异常
            //表示登录成功
            PrintJson.printJsonFlag(response,true);

        }catch(Exception e){
            e.printStackTrace();
            //一旦程序执行了catch块的信息，说明业务层为我们验证登录失败为控制器抛出了异常
            //表示登录失败
            String msg = e.getMessage();
            /*
                我们现在作为controller，需要为Ajax请求提供多项信息

                可以有两种手段来处理：
                （1）将多项信息打包成map，将map解析成json串
                （2）创建一个Vo
                        private boolean success;
                        private String msg;
                如果对于创建的信息将来还会大量使用，我们创建一个Vo类，使用方便
                如果对于创建的信息只有在这个需求中能够使用，我们使用map就可以了
             */
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}


