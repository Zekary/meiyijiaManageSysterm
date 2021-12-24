package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//用户退出功能

public class LogoutServlet extends HttpServlet {
    //构造方法，不能缺少，不然会报错
    public LogoutServlet(){
        super();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        从httpSession域对象里面移除user对象
        System.out.println("logoutsuccess");
        req.getSession().removeAttribute("username");
        req.getSession().removeAttribute("store");
        req.getSession().removeAttribute("userid");
        req.getSession().removeAttribute("grade");
        req.getSession().removeAttribute("password");
//        跳转回到登录页面
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
