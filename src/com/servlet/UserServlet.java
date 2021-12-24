package com.servlet;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.User;
import com.service.FinancialService;
import com.service.UserService;

public class UserServlet extends HttpServlet{
    //构造方法，不能缺少，不然会报错
    public UserServlet() {
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
        //1.通过UserService查询全部员工
        //2.把全部图书保存到request域中
        //3.请求转发到superindex.jsp中

        //调用service获取数据库中的表
        UserService userService = new UserService();

        //根据userid，从数据库获取数据
        List<User> userlist = userService.getUserlist();

        //把employeelist对象存入到request域对象中
        request.setAttribute("userlist", userlist);

        //请求转发
        request.getRequestDispatcher("usermanage.jsp").forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取form表单的参数，然后封装成employee对象，保存到数据库，然后跳到员工管理页面
        User u = new User();
        u.setUserName(request.getParameter("username"));
        u.setUserPsd(request.getParameter("userpsd"));
        u.setStoreName(request.getParameter("storename"));
        //调用service函数增加到数据库
        UserService userService = new UserService();
        userService.add(u);
        //重新读取数据库里面的员工数据
        response.sendRedirect("UserServlet?action=list");
    }

    protected void getu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取图书编号，调用serivice函数对数据进行修改并保存到数据库中，再次读取数据
        int userid = Integer.parseInt(request.getParameter("userid"));
        //调用service函数对数据进行修改
        UserService userService = new UserService();
        User u = userService.getu(userid);
        //保存到域对象
        request.setAttribute("user", u);
        //请求转发到update.jsp里面
        request.getRequestDispatcher("user_update.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //封装数据
        User u = new User();
        u.setId(Integer.parseInt(request.getParameter("userid")));
        u.setUserName(request.getParameter("username"));
        u.setUserPsd(request.getParameter("psd"));
        u.setStoreName(request.getParameter("storename"));
        //调用service函数增加到数据库
        UserService userService = new UserService();
        userService.update(u);
        //重新读取数据库里面的员工数据
        response.sendRedirect("UserServlet?action=list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数的id
        int userid = Integer.parseInt(request.getParameter("userid"));
        //2.调用service的deleteemp删除对应的图书
        UserService userService = new UserService();
        userService.delete(userid);
        //3.重定向会员工管理页面
        response.sendRedirect("UserServlet?action=list");
    }

    protected void delS (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String comp = request.getParameter("comp");
        String[] strArr = comp.split(",");
        System.out.println(strArr);

        int[] intArr = new int[strArr.length];  //定义一个长度与上述的字符串数组长度相通的整型数组
        for(int a=0;a<strArr.length;a++){
            intArr[a] = Integer.valueOf(strArr[a]);  //然后遍历字符串数组，使用包装类Integer的valueOf方法将字符串转为整型
        }

        //2.调用service的delS方法删除对应的员工
        FinancialService financialService = new FinancialService();
        financialService.deletes(intArr);

        //3.重定向会员工管理页面
        response.sendRedirect("UserServlet?action=list");
    }

}
