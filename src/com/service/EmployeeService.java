package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Dao.Employee;

public class EmployeeService {
    public List<Employee> getEmplist(int userid,int grade) {
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

    public void add(Employee employee) {
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
        //结果集
        ResultSet rs = null;
        try {
            //useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true
            String URL = "jdbc:mysql://127.0.0.1:3306/Market?characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT&allowPublicKeyRetrieval=true";
            String USERNAME = "root";
            String PASSWORD = "root";
            //获取连接
            //a.导入驱动，加载具体的驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //b.与数据库建立连接
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(conn == null);
            //c编写插入的sql语句
            String sql = "insert into t_emp(ename,age,sex,sal,birthday,edate,storeid) value(?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, employee.getEmpName());
            stmt.setString(2, employee.getAge());
            stmt.setString(3, employee.getSex());
            stmt.setDouble(4, employee.getSalary());
            stmt.setString(5, employee.getBirthday());
            stmt.setString(6, employee.getEdate());
            stmt.setInt(7, employee.getStoreid());
            stmt.execute();
            System.out.println("员工数据添加成功！");
        } catch (Exception e) {
            System.out.println("some error!");
            System.out.println(e.toString());
        } finally {
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
    }

    public Employee gete(int id) {
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
        //结果集
        ResultSet rs = null;
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
            //c.发送sql语句，执行sql语句，查询指定的一条数据。
            stmt = conn.prepareStatement("select * from t_emp where empid= '" + id + "' ");
            rs = stmt.executeQuery();
            Employee emp = new Employee();
            if (rs.next()) {
                emp.setEmpid(rs.getInt(1));
                emp.setEmpName(rs.getString(2));
                emp.setAge(rs.getString(3));
                emp.setSex(rs.getString(4));
                emp.setSalary(rs.getDouble(5));
                emp.setBirthday(rs.getString(6));
                emp.setEdate(rs.getString(7));
                emp.setStoreid(rs.getInt(8));
            }
            return emp;
        } catch (Exception e) {
            System.out.println("some error!");
            System.out.println(e.toString());
        } finally {
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
        System.out.println("gete worse");
        return null;
    }


    public void update(Employee employee) {
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
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

            //c.发送sql语句，执行sql语句，修改指定的一条数据。
            stmt = conn.prepareStatement("update t_emp SET ename= '" + employee.empName + "' ,  age =" +
                    " '" + employee.age + "' , sex = '" + employee.sex + "' , sal = '" + employee.salary + "', birthday = " +
                    "'" + employee.birthday + "', edate = '" + employee.edate + "', storeid = '" + employee.storeid + "'  where empid = '" + employee.empid + "' ");
            stmt.execute();

        } catch (Exception e) {
            System.out.println("some error!");
            System.out.println(e.toString());
        } finally {
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
    }

    //删除操作
    public void delete(int id) {
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
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
            //c.发送sql语句
            stmt = conn.prepareStatement("delete from t_emp where empid = '"+id+"' ");
            stmt.execute();
        }catch (Exception e){
            System.out.println("some error!");
            System.out.println(e.toString());
        } finally {
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
    }

    //批量删除数据
    public void deletes(int[] arr){
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
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
            //c.发送sql语句
            for (int i=0;i<arr.length;i++){
                stmt = conn.prepareStatement("delete from t_emp where empid = '"+arr[i]+"' ");
                stmt.execute();
            }
        }catch (Exception e){
            System.out.println("some error!");
            System.out.println(e.toString());
        } finally {
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
    }



}
