package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import com.Dao.Employee;
import com.service.EmployeeService;
import com.service.SalaryService;
public class SalaryServlet extends HttpServlet{
    //查询指定的emp
    //构造方法，不能缺少，不然会报错
    public SalaryServlet() {
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

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //调用service获取数据库中的表
        SalaryService salaryService = new SalaryService();
        //从session中获取userid和grade
        int userid = (int) request.getSession().getAttribute("userid");
        int grade = (int) request.getSession().getAttribute("grade");

        //根据userid，从数据库获取数据
        List<Employee> salaryList = salaryService.getsallist(userid,grade);

        //把employeelist对象存入到request域对象中
        request.setAttribute("salarylist", salaryList);

        //请求转发
        request.getRequestDispatcher("salarymanage.jsp").forward(request, response);
    }

}
