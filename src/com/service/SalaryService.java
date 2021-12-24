package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Dao.Employee;
public class SalaryService {
    public List<Employee> getsallist(int userid,int grade) {
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
        //结果集
        ResultSet rs = null;
        //创建list集合，储存员工对象
        List<Employee> emplist = new ArrayList<Employee>();
        try {
            String URL = "jdbc:mysql://127.0.0.1:3306/Market?characterEncoding=UTF-8&useSSL=false";
            String USERNAME = "root";
            String PASSWORD = "root";
            //获取连接
            //a.导入驱动，加载具体的驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //b.与数据库建立连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(conn == null);
            //c.发送sql语句，执行sql语句
            stmt = conn.prepareStatement("select * from t_emp");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpid(rs.getInt(1));
                emp.setEmpName(rs.getString(2));
                emp.setAge(rs.getString(3));
                emp.setSex(rs.getString(4));
                emp.setSalary(rs.getDouble(5));
                emp.setBirthday(rs.getString(6));
                emp.setEdate(rs.getString(7));
                emp.setStoreid(rs.getInt(8));
                int id = emp.getStoreid();
                if (id == userid || grade==1)
                    emplist.add(emp);
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
        return emplist;
    }

}
