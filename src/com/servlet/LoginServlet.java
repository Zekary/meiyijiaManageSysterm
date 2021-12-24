package com.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Dao.User;
public class LoginServlet extends HttpServlet{
    //构造方法，不能缺少，不然会报错
    public LoginServlet(){
        super();
    }

    //get方法
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);  //直接写doPost(request,response)可能会有错误，this.post()确保能调用成功。
    }
    //post方法传输数据
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取登录信息
        String username = request.getParameter("name");
        String psd = request.getParameter("pass");
        User userinfo = new User();
        userinfo.setUserName(username);
        userinfo.setUserPsd(psd);
        try {
            String URL = "jdbc:mysql://127.0.0.1:3306/Market?characterEncoding=UTF-8";
            String USERNAME = "root";
            String PASSWORD = "root";
            //目的：在数据库中查看是否存在此用户
            Connection connection = null;
            ResultSet resultSet;

            //a.导入驱动，加载具体的驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //b.与数据库建立连接
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(connection == null);
            //c.发送sql语句，执行sql语句
            PreparedStatement ps = connection.prepareStatement("select * from t_user");
            resultSet = ps.executeQuery();
            int type = 0;
            while (resultSet.next()) {
                int sysid = resultSet.getInt(1);
                String sysusername = resultSet.getString(2);
                String syspassword = resultSet.getString(3);
                String sysstorename = resultSet.getString(4);
                int sysgrade =  resultSet.getInt(5);
                System.out.println(sysid);
                System.out.println(sysusername);
                System.out.println(syspassword);
                System.out.println(sysstorename);
                if (sysusername.equals(username) && syspassword.equals(psd)) {
                    request.getSession().setAttribute("username", sysusername);
                    request.getSession().setAttribute("store", sysstorename);
                    request.getSession().setAttribute("password",syspassword);
                    request.getSession().setAttribute("userid",sysid);
                    request.getSession().setAttribute("grade",sysgrade);
                    request.getSession().setMaxInactiveInterval(-1);
                    response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                    if (sysgrade==1)
                        response.setHeader("Location","superindex.jsp");
                    else
                    response.setHeader("Location", "index.jsp");
                } else if (sysusername.equals(username)) {
                    out.print("<script language='javascript'>alert('密码错误')</script>");
                    out.print("<script>window.location.href='login.jsp'</script>");
                    type = 1;
                }
            }
            if (type == 0){
                out.print("<script language='javascript'>alert('用户名不存在')</script>");

                out.print("<script>window.location.href='login.jsp'</script>");
            }

        } catch (Exception e) {
            out.println("some error");
            out.println(e.toString());
        }
    }
}
