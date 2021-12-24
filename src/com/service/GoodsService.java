package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.Dao.Goods;

public class GoodsService {
    public List<Goods> getGoodslist(int userid,int grade) {
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
        //结果集
        ResultSet rs = null;
        //创建list集合，储存员工对象
        List<Goods> goodslist = new ArrayList<Goods>();
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
            stmt = conn.prepareStatement("select * from t_goods");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Goods go = new Goods();
                go.setGoodsid(rs.getInt(1));
                go.setGname(rs.getString(2));
                go.setNum(rs.getInt(3));
                go.setPrice(rs.getDouble(4));
                go.setFactory(rs.getString(5));
                go.setStorageday(rs.getInt(6));
                go.setProductdate(rs.getString(7));
                go.setStoreid(rs.getInt(8));
                int id = go.storeid;
                if (id == userid || grade==1)
                    goodslist.add(go);
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
        return goodslist;
    }

    public void add(Goods goods) {
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
            String sql = "insert into t_goods(gname,num,price,factory,storageday,productdate,storeid) value(?,?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, goods.gname);
            stmt.setInt(2, goods.num);
            stmt.setDouble(3, goods.price);
            stmt.setString(4, goods.factory);
            stmt.setInt(5, goods.storageday);
            stmt.setString(6, goods.productdate);
            stmt.setInt(7, goods.storeid);
            stmt.execute();
            System.out.println("商品数据添加成功！");
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

    public Goods getg(int id) {
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
            stmt = conn.prepareStatement("select * from t_goods where goodsid= '" + id + "' ");
            rs = stmt.executeQuery();
            Goods goods = new Goods();
            if (rs.next()) {
                goods.setGoodsid(rs.getInt(1));
                goods.setGname(rs.getString(2));
                goods.setNum(rs.getInt(3));
                goods.setPrice(rs.getDouble(4));
                goods.setFactory(rs.getString(5));
                goods.setStorageday(rs.getInt(6));
                goods.setProductdate(rs.getString(7));
                goods.setStoreid(rs.getInt(8));
            }
            return goods;
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

    public void update(Goods go) {
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
            stmt = conn.prepareStatement("update t_goods SET gname= '" + go.gname + "' ,  num =" +
                    " '" + go.num + "' , price = '" + go.price + "' , factory = '" + go.factory + "', storageday = " +
                    "'" + go.storageday + "', productdate = '" + go.productdate + "', storeid = '" + go.storeid + "'  where goodsid = '" + go.goodsid + "' ");
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
            stmt = conn.prepareStatement("delete from t_goods where goodsid = '"+id+"' ");
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
                stmt = conn.prepareStatement("delete from t_goods where goodsid = '"+arr[i]+"' ");
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


    //计算是否过期
    public boolean culdate(String productdate, int storageday){
        //1.获取参数
        String[] strArr = productdate.split("-");
        int[] intArr = new int[strArr.length];  //定义一个长度与上述的字符串数组长度相通的整型数组
        for(int a=0;a<strArr.length;a++){
            intArr[a] = Integer.valueOf(strArr[a]);  //然后遍历字符串数组，使用包装类Integer的valueOf方法将字符串转为整型
        }

        //获取当前系统时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nowday = formatter.format(date);
        String[] nStrArr = nowday.split("-");
        int[] nintArr = new int[nStrArr.length];
        for (int a=0;a<nStrArr.length;a++){
            nintArr[a] = Integer.valueOf(nStrArr[a]);
        }

        //计算天数间隔并进行比较
        LocalDate star = LocalDate.of(intArr[0],intArr[1],intArr[2]);
        LocalDate end = LocalDate.of(nintArr[0],nintArr[1],nintArr[2]);
        long l = ChronoUnit.DAYS.between(star, end);
        int datelong = (int)l;

        if(datelong>storageday*365)
            return true;
        else
            return false;
    }

    //查询过期商品列表
    public List<Goods> getoutdatelist(int userid) {
        //连接
        Connection conn = null;
        //预编译对象
        PreparedStatement stmt = null;
        //结果集
        ResultSet rs = null;
        //创建list集合，储存员工对象
        List<Goods> outdatelist = new ArrayList<Goods>();
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
            stmt = conn.prepareStatement("select * from t_goods");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Goods go = new Goods();
                go.setGoodsid(rs.getInt(1));
                go.setGname(rs.getString(2));
                go.setNum(rs.getInt(3));
                go.setPrice(rs.getDouble(4));
                go.setFactory(rs.getString(5));
                go.setStorageday(rs.getInt(6));
                go.setProductdate(rs.getString(7));
                go.setStoreid(rs.getInt(8));
                int id = go.storeid;
                if (id == userid && culdate(go.productdate,go.storageday))
                    outdatelist.add(go);
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
        return outdatelist;
    }


    //删除过期商品操作
    public void outdelete(int id) {
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
            stmt = conn.prepareStatement("delete from t_goods where goodsid = '"+id+"' ");
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
    public void outdeletes(int[] arr){
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
                stmt = conn.prepareStatement("delete from t_goods where goodsid = '"+arr[i]+"' ");
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
