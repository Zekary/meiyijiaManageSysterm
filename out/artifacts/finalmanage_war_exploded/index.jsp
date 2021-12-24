<%--
  Created by IntelliJ IDEA.
  User: toumasayasu
  Date: 2021/11/29
  Time: 3:26 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>首页</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <link rel="stylesheet" type="text/css" href="css/page.css">
    <link rel="stylesheet" type="text/css" href="css/calendar.css">
    <link rel="stylesheet" href="https://lib.sinaapp.com/js/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/plugins/css/icons.min.css">
</head>
<body>
<meta charset="UTF-8">
<%
    //获取用户的信息
    String username = (String) session.getAttribute("username");
    String store = (String) session.getAttribute("store");
%>
<div class="page" id="app">
    <div class="nav-left">
        <img class="logo" src="https://www.meiyijia.com.cn/images/logo.png"/>
        <div class="LogoName">
            美宜佳超市管理系统
        </div>
        <div class="navDiv">
            <%--            左边侧边栏--%>
            <div class="nav-list">
                <ul>
                    <li class="nav-tab waves-effect">
                        <a href="javascript:void[0]" class="li-a active">
                            <a href="EmployeeServlet?action=list" class="li-a" target="iframe">
                                <i class='bx bx-layer'></i> 员工管理 <span
                                    class="badge badge-pill badge-primary float-right">2</span>
                            </a>
                        </a>
                        <div class="nav-box">
                            <a href="emp_recruit.jsp" class="li-a-a" target="iframe">发布招聘信息</a>
                        </div>
                    </li>

                    <li class="nav-tab nav-ul">
                        <a href="javascript:void[0]" class="li-a">
                            <a href="GoodsServlet?action=list" target="iframe" class="li-a">
                                <i class='bx bx-cog'></i> 商品信息管理<i class='bx bx-chevron-right'
                                                                   style="float: right;"></i>
                                <span class="badge badge-pill badge-primary float-right">3</span>
                            </a>
                        </a>
                        <div class="nav-box">
                            <a href="GoodsServlet?action=outlist" class="li-a-a" target="iframe">过期商品检索</a>
                        </div>
                    </li>

                    <li class="nav-tab nav-ul">
                        <a href="javascript:void[0]" class="li-a">
                            <a href="FinancialServlet?action=list" target="iframe" class="li-a">
                                <i class='bx bx-cog'></i> 财务信息管理<i class='bx bx-chevron-right'
                                                                   style="float: right;"></i>
                                <span class="badge badge-pill badge-primary float-right">3</span>
                            </a>
                        </a>
                        <div class="nav-box">
                            <a href="FinanChartServlet?action=JqueryPost" class="li-a-a" target="iframe">财务报表</a>
                        </div>
                        <div class="nav-box">
                            <a href="SalaryServlet?action=list" class="li-a-a" target="iframe">工资发放表</a>
                        </div>
                    </li>

                    <%--                <li class="nav-tab waves-effect">--%>
                    <%--                    <a href="javascript:void[0]" class="li-a active">--%>
                    <%--                        <a href="FinancialServlet?action=list" class="li-a" target="iframe">--%>
                    <%--                            <i class='bx bx-layer'></i> 财务管理 <span class="badge badge-pill badge-primary float-right">2</span>--%>
                    <%--                        </a>--%>
                    <%--                    </a>--%>
                    <%--                </li>--%>

                    <li class="nav-tab waves-effect">
                        <a href="javascript:void[0]" class="li-a active">
                            <a href="changepassword.jsp" class="li-a" target="iframe">
                                <i class='bx bx-home-smile'></i> 修改密码
                            </a>
                        </a>
                    </li>
                    <div class='calendar' id='calendar'></div>
                </ul>

            </div>
        </div>
    </div>


    <%--右边内容--%>
    <div class="nav-right bg-dark">
        <div class="nav-top bg-light row justify-content-between bg-dark">

            <h2><span class="badge badge-dark col">
            <%
                out.println("用户名：" + username + "       门店：" + store);
            %>
          </span></h2>
            <button type="button" class="btn btn-outline-info col-md-auto"
                    onclick="window.location.href = 'LogoutServlet'">注销
            </button>
        </div>
        <div class="content-page ">
            <iframe name="iframe" width="100%" height="100%" frameborder="0" src="EmployeeServlet?action=list"
                    style="width: 102%"></iframe>
        </div>
    </div>
</div>

<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        let navflag = false;
        $('.nav-tab').click(function () {
            $(this).siblings().each(function () {
                $(this).removeClass('a_active');
                // $(this).removeClass('a_active');
                $(this).find('.nav-box').css('height', '0')
                //关闭右侧箭头
                if ($(this).attr('class').indexOf('nav-ul') != -1) {
                    $(this).find('.bx-chevron-right').css('transform', 'rotateZ(0deg)')
                    $(this).find('.bx-chevron-right').css('transition', 'all .5s')
                    $(this).removeClass('nav-show')
                    // $(this).find('div').removeClass('nav-box')
                }
            })
            //当前选中
            $(this).addClass('a_active')
            $(this).find('.li-a').addClass('active')
            // 打开右侧箭头
            $(this).find('.bx-chevron-right').css('transform', 'rotateZ(90deg)')
            $(this).find('.bx-chevron-right').css('transition', 'all .5s')
            $(this).addClass('nav-show')
            // $(this).find('div').addClass('nav-box')
        })
        /* 二级菜单a点击事件 */
        $(".li-a-a").click(function () {
            $(".li-a-a").each(function () {
                $(this).removeClass('active-li-a');
            })
            $(this).addClass('active-li-a');
        })
    })
    // const vue = new Vue({
    // 	el:'#app',
    // 	data:{

    // 	},
    // 	methods:{
    // 		liCli(){

    // 		}
    // 	}
    // })
</script>
</body>
</html>
