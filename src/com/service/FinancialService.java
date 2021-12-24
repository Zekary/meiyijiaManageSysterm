package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Dao.Financial;

public class FinancialService {
    public List<Financial> getFinanciallist(int userid,int grade) {
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
                int id = finan.storeid;
                if (id == userid || grade==1)
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
        return financiallist;
    }


    public void add(Financial financial) {
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
            String sql = "insert into t_financial(storeid,income,expend,profict,month) value(?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, financial.storeid);
            stmt.setDouble(2, financial.income);
            stmt.setDouble(3, financial.expend);
            stmt.setDouble(4, financial.profict);
            stmt.setString(5, financial.month);
            stmt.execute();
            System.out.println("财务数据添加成功！");
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

    public Financial getf(int id) {
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
            stmt = conn.prepareStatement("select * from t_financial where id= '" + id + "' ");
            rs = stmt.executeQuery();
            Financial financial = new Financial();
            if (rs.next()) {
                financial.setFinancialid(rs.getInt(1));
                financial.setStoreid(rs.getInt(2));
                financial.setIncome(rs.getDouble(3));
                financial.setExpend(rs.getDouble(4));
                financial.setProfict(rs.getDouble(5));
                financial.setMonth(rs.getString(6));
            }
            return financial;
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

    public void update(Financial financial) {
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
            stmt = conn.prepareStatement("update t_financial SET storeid = '" + financial.storeid + "' ,  income =" +
                    " '" + financial.income + "' , expend = '" + financial.expend + "' , profict = '" + financial.profict + "', month = " +
                    "'" + financial.month + "' where id = '" + financial.financialid + "' ");
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
            stmt = conn.prepareStatement("delete from t_financial where id = '"+id+"' ");
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
                stmt = conn.prepareStatement("delete from t_financial where id = '"+arr[i]+"' ");
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
