package com.servlet;

import com.Dao.Financial;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FinanChartServlet extends HttpServlet{
    //构造方法，不能缺少，不然会报错
    public FinanChartServlet() {
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

    protected void JqueryPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("JqueryPost方法调用了！");
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
        //结果集
        ResultSet rs = null;
        //创建list集合，储存员工对象
        List<Financial> financiallist = new ArrayList<Financial>();
        try {
            String URL = "jdbc:mysql://127.0.0.1:3306/Market?characterEncoding=UTF-8&useSSL=false";
            String USERNAME = "root";
            String PASSWORD = "root";
            int userid = (int) request.getSession().getAttribute("userid");
            //获取连接
            //a.导入驱动，加载具体的驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //b.与数据库建立连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(conn == null);
            //c.发送sql语句，执行sql语句
            stmt = conn.prepareStatement("select * from t_financial");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Financial finan = new Financial();
                finan.setFinancialid(rs.getInt(1));
                finan.setStoreid(rs.getInt(2));
                finan.setIncome(rs.getDouble(3));
                finan.setExpend(rs.getDouble(4));
                finan.setProfict(rs.getDouble(5));
                finan.setMonth(rs.getString(6));
                System.out.println(finan.month);
                int id = finan.storeid;
                if (id == userid)
                    financiallist.add(finan);
            }
        } catch (Exception e) {
            System.out.println("some error!");
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        }

        request.setAttribute("financiallist", financiallist);

        //请求转发
        request.getRequestDispatcher("finan_chart.jsp").forward(request, response);
    }
}
