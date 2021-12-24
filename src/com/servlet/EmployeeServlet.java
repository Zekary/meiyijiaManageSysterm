package com.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import com.Dao.Employee;
import com.service.EmployeeService;

public class EmployeeServlet extends HttpServlet {
    //查询指定的emp
    //构造方法，不能缺少，不然会报错
    public EmployeeServlet() {
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
        //1.通过EmployeeService查询全部员工
        //2.把全部图书保存到request域中
        //3.请求转发到employeemanage.jsp中

        //调用service获取数据库中的表
        EmployeeService employeeService = new EmployeeService();
        //从session中获取userid和grade
        int userid = (int) request.getSession().getAttribute("userid");
        int grade = (int) request.getSession().getAttribute("grade");

        //根据userid，从数据库获取数据
        List<Employee> employeeList = employeeService.getEmplist(userid,grade);

        //把employeelist对象存入到request域对象中
        request.setAttribute("employeelist", employeeList);

        //请求转发
        request.getRequestDispatcher("employeemanage.jsp").forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取form表单的参数，然后封装成employee对象，保存到数据库，然后跳到员工管理页面
        Employee employee = new Employee();
        employee.setEmpid(0);
        employee.setEmpName(request.getParameter("empName"));
        employee.setAge(request.getParameter("age"));
        employee.setSex(request.getParameter("sex"));
        employee.setSalary(Double.parseDouble(request.getParameter("salary")));
        employee.setBirthday(request.getParameter("birthday"));
        employee.setEdate(request.getParameter("edate"));
        employee.setStoreid((int) request.getSession().getAttribute("userid"));

        //调用service函数增加到数据库
        EmployeeService employeeService = new EmployeeService();
        employeeService.add(employee);

        //重新读取数据库里面的员工数据
        response.sendRedirect("EmployeeServlet?action=list");
    }

    protected void gete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取图书编号，调用serivice函数对数据进行修改并保存到数据库中，再次读取数据
        int empid = Integer.parseInt(request.getParameter("empid"));
        //调用service函数对数据进行修改
        EmployeeService employeeService = new EmployeeService();
        Employee employee = employeeService.gete(empid);
        //保存到域对象
        request.setAttribute("employee", employee);
        //请求转发到update.jsp里面
        request.getRequestDispatcher("emp_update.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //获取修改信息
        int empid = Integer.parseInt(request.getParameter("empid"));
        String empname = request.getParameter("empname");
        String empage = request.getParameter("empage");
        String sex = request.getParameter("sex");
        double sal = Double.parseDouble(request.getParameter("sal"));
        String birth = request.getParameter("birth");
        String edate = request.getParameter("edate");

        //封装成employee
        Employee employee = new Employee();
        employee.setEmpid(empid);
        employee.setEmpName(empname);
        employee.setAge(empage);
        employee.setSex(sex);
        employee.setSalary(sal);
        employee.setBirthday(birth);
        employee.setEdate(edate);
        employee.setStoreid((int) request.getSession().getAttribute("userid"));

        //调用service函数增加到数据库
        EmployeeService employeeService = new EmployeeService();
        employeeService.update(employee);

        //重新读取数据库里面的员工数据
        response.sendRedirect("EmployeeServlet?action=list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数的id
        int empid = Integer.parseInt(request.getParameter("empid"));
        //2.调用service的deleteemp删除对应的图书
        EmployeeService employeeservice = new EmployeeService();
        employeeservice.delete(empid);
        //3.重定向会员工管理页面
        response.sendRedirect("EmployeeServlet?action=list");
    }

    protected void delS (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String comp = request.getParameter("comp");
        String[] strArr = comp.split(",");

        int[] intArr = new int[strArr.length];  //定义一个长度与上述的字符串数组长度相通的整型数组
        for(int a=0;a<strArr.length;a++){
            intArr[a] = Integer.valueOf(strArr[a]);  //然后遍历字符串数组，使用包装类Integer的valueOf方法将字符串转为整型
        }
        //2.调用service的delS方法删除对应的员工
        EmployeeService employeeservice = new EmployeeService();
        employeeservice.deletes(intArr);

        //3.重定向会员工管理页面
        response.sendRedirect("EmployeeServlet?action=list");
    }


}
