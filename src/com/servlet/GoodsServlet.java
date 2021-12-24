package com.servlet;

import com.Dao.Goods;
import com.service.GoodsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;


public class GoodsServlet extends HttpServlet {
    //构造方法，不能缺少，不然会报错
    public GoodsServlet() {
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
        //1.通过GoodsService查询全部商品
        //2.把全部商品保存到request域中
        //3.请求转发到goodsmanage.jsp中

        //调用service获取数据库中的表
        GoodsService goodsService = new GoodsService();
        //从session中获取userid
        int userid = (int) request.getSession().getAttribute("userid");
        int grade = (int) request.getSession().getAttribute("grade");
        //根据userid，从数据库获取数据
        List<Goods> goodslist = goodsService.getGoodslist(userid,grade);

        //把goodslist对象存入到request域对象中
        request.setAttribute("goodslist", goodslist);

        //请求转发
        request.getRequestDispatcher("goodsmanage.jsp").forward(request, response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取form表单的参数，然后封装成goods对象，保存到数据库，然后跳到员工管理页面
        Goods good = new Goods();
        good.setGoodsid(0);
        good.setGname(request.getParameter("gname"));
        good.setNum(Integer.parseInt(request.getParameter("num")));
        good.setPrice(Double.parseDouble(request.getParameter("price")));
        good.setFactory(request.getParameter("factory"));
        good.setStorageday(Integer.parseInt(request.getParameter("storageday")));
        good.setProductdate(request.getParameter("productdate"));
        good.setStoreid((int) request.getSession().getAttribute("userid"));

        //调用service函数增加到数据库
        GoodsService goodsService = new GoodsService();
        goodsService.add(good);

        //重新读取数据库里面的员工数据
        response.sendRedirect("GoodsServlet?action=list");
    }

    protected void getg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取图书编号，调用serivice函数对数据进行修改并保存到数据库中，再次读取数据
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        //调用service函数对数据进行修改
        GoodsService goodsservice = new GoodsService();
        Goods goods = goodsservice.getg(goodsid);

        //保存到域对象
        request.setAttribute("goods", goods);
        //请求转发到update.jsp里面
        request.getRequestDispatcher("goods_update.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //封装数据
        Goods good = new Goods();
        good.setGoodsid(Integer.parseInt(request.getParameter("goodsid")));
        good.setGname(request.getParameter("gname"));
        good.setNum(Integer.parseInt(request.getParameter("num")));
        good.setPrice(Double.parseDouble(request.getParameter("price")));
        good.setFactory(request.getParameter("factory"));
        good.setStorageday(Integer.parseInt(request.getParameter("storageday")));
        good.setProductdate(request.getParameter("productdate"));
        good.setStoreid((int) request.getSession().getAttribute("userid"));

        //调用service函数增加到数据库
        GoodsService goodsService = new GoodsService();
        goodsService.update(good);

        //重新读取数据库里面的员工数据
        response.sendRedirect("GoodsServlet?action=list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数的id
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        //2.调用service的deleteemp删除对应的图书
        GoodsService goodsService = new GoodsService();
        goodsService.delete(goodsid);
        //3.重定向会员工管理页面
        response.sendRedirect("GoodsServlet?action=list");
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
        GoodsService goodsService = new GoodsService();
        goodsService.deletes(intArr);

        //3.重定向会员工管理页面
        response.sendRedirect("GoodsServlet?action=list");
    }


    //获取过期商品信息
    protected void outlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过GoodsService查询过期商品
        //2.把全部商品保存到request域中
        //3.请求转发到goodsmanage.jsp中

        //调用service获取数据库中的表
        GoodsService goodsService = new GoodsService();
        //从session中获取userid
        int userid = (int) request.getSession().getAttribute("userid");

        //根据userid，从数据库获取数据
        List<Goods> outdatelist = goodsService.getoutdatelist(userid);

        //把goodslist对象存入到request域对象中
        request.setAttribute("outdatelist", outdatelist);

        //请求转发
        request.getRequestDispatcher("outdategoods.jsp").forward(request, response);
    }


    //清除过期商品
    protected void outdelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求参数的id
        int outid = Integer.parseInt(request.getParameter("outid"));
        //2.调用service的deleteemp删除对应的图书
        GoodsService goodsService = new GoodsService();
        goodsService.outdelete(outid);
        //3.重定向会员工管理页面
        response.sendRedirect("GoodsServlet?action=outlist");
    }

    //清除
    protected void outdelS (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取参数
        String comp = request.getParameter("comp");
        String[] strArr = comp.split(",");
        int[] intArr = new int[strArr.length];  //定义一个长度与上述的字符串数组长度相通的整型数组
        for(int a=0;a<strArr.length;a++){
            intArr[a] = Integer.valueOf(strArr[a]);  //然后遍历字符串数组，使用包装类Integer的valueOf方法将字符串转为整型
        }

        //2.调用service的delS方法删除对应的员工
        GoodsService goodsService = new GoodsService();
        goodsService.outdeletes(intArr);

        //3.重定向会员工管理页面
        response.sendRedirect("GoodsServlet?action=list");
    }
}
