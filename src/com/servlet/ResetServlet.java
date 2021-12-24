package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import com.service.ResetService;
public class ResetServlet extends HttpServlet {

    //构造方法，不能缺少，不然会报错
    public ResetServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            //获取action业务鉴别字符串，得到相应的业务 方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用目标业务，方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取jsp页面的修改信息
        String repass =  request.getParameter("resurePassword");
        String pass = request.getParameter("oldPassword");
        int userid = (int) request.getSession().getAttribute("userid");
        PrintWriter out = response.getWriter();
        String syspass = (String) request.getSession().getAttribute("password");
        if (!syspass.equals(pass)){
            out.print("<script language='javascript'>alert('原密码错误！')</script>");
            out.print("<script>window.location.href='changepassword.jsp'</script>");
        }
        //2.调用Resetservice的方法
        ResetService resetService = new ResetService();
        resetService.reset(repass,userid);

        //3.重定向会员工管理页面
        response.sendRedirect("changepassword.jsp");
    }

}
