package com.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Dao.Employee;
import com.Dao.Goods;
import com.Dao.User;
public class UserService {
    public List<User> getUserlist() {
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
        //结果集
        ResultSet rs = null;
        //创建list集合，储存员工对象
        List<User> userlist = new ArrayList<User>();
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
            stmt = conn.prepareStatement("select * from t_user");
            rs = stmt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setUserPsd(rs.getString(3));
                u.setStoreName(rs.getString(4));
                u.setGrade(rs.getInt(5));
                if(u.grade==2)
                userlist.add(u);
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
        return userlist;
    }

    public void add(User user) {
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
            String sql = "insert into t_user(username,psd,storename,grade) value(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.userName);
            stmt.setString(2, user.userPsd);
            stmt.setString(3, user.storeName);
            stmt.setInt(4,2);
            stmt.execute();
            System.out.println("店长数据添加成功！");
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

    public User getu(int id) {
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
            stmt = conn.prepareStatement("select * from t_user where id = '" + id + "' ");
            rs = stmt.executeQuery();
            User u = new User();
            if (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setUserPsd(rs.getString(3));
                u.setStoreName(rs.getString(4));
            }
            return u;
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
        System.out.println("getu worse");
        return null;
    }

    public void update(User u) {
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
            stmt = conn.prepareStatement("update t_user SET username= '" + u.userName + "' ,  psd =" +
                    " '" + u.userPsd + "' , storename = '" + u.storeName + "' where id = '" + u.id + "' ");
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
            //用户删除
            stmt = conn.prepareStatement("delete from t_user where id = '"+ id +"' ");
            stmt.execute();
            //对应商品删除
            stmt = conn.prepareStatement("delete from t_goods where storeid = '"+ id +"'");
            stmt.execute();
            //对应财政删除
            stmt = conn.prepareStatement("delete from t_financial where storeid = '"+ id +"'");
            stmt.execute();
            //对应员工删除
            stmt = conn.prepareStatement("delete from t_emp where storeid = '"+ id +"'");
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
            //用户删除
            for (int i=0;i<arr.length;i++){
                stmt = conn.prepareStatement("delete from t_user where id = '"+arr[i]+"' ");
                stmt.execute();
            }
            //商品删除
            for (int i=0;i<arr.length;i++){
                stmt = conn.prepareStatement("delete from t_goods where storeid = '"+arr[i]+"' ");
                stmt.execute();
            }
            //财政删除
            for (int i=0;i<arr.length;i++){
                stmt = conn.prepareStatement("delete from t_financial where storeid = '"+arr[i]+"' ");
                stmt.execute();
            }
            //员工删除
            for (int i=0;i<arr.length;i++){
                stmt = conn.prepareStatement("delete from t_goods where storeid = '"+arr[i]+"' ");
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
