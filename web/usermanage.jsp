<%--
  Created by IntelliJ IDEA.
  User: toumasayasu
  Date: 2021/12/17
  Time: 6:26 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/usermanage.css">
    <title>用户管理</title>
    <style>
        a{
            color: #FFFF;
            text-decoration:none;
        }
        a:link {
            color: #FFF;
            text-decoration: none;
        }
        a:visited {
            color: #FFF;
            text-decoration: none;
        }
        a:hover {
            color: #FFF;
            text-decoration: none;
        }
        tbody {
            display: block;
            height: 400px;
            overflow-y: scroll;
        }

        thead {
            width: calc(100% - 1em);
        }

        th.last {
            width: 17px;
        }

        thead, tbody tr {
            display: table;
            width: 100%;
            table-layout: fixed;
        }
    </style>
</head>

<body style="margin-left: 4%; overflow-y:Scroll;overflow-x:hidden">
<div class="row">
    <nav class="navbar navbar-light">
        <button type="button" class="btn btn-outline-warning my-sm-1" id="all">全选</button>
        <button type="button" class="btn btn-outline-warning my-sm-1" id="reverse"
                style="margin-left: 25px;">反选</button>
        <button type=" button" class="btn btn-outline-danger my-sm-1" id="select"
                style="margin-left: 25px; margin-right: 4cm;">对选择的进行删除</button>
        <form class="form-inline">
            <input class="form-control mr-sm-2" id="search" type="search" placeholder="Search" aria-label="Search" style="margin-top: 16px">
        </form>
        <button class="btn btn-outline-success my-sm-1" id="search_btn" type="submit">搜索</button>
        <button type="button" class="btn btn-outline-primary my-sm-1" id="add"
                style="margin-left: 25px;">增加店长</button>
    </nav>
</div>
<div class="row">
    <table class="table table-hover table-bordered" style="width: 95%;">
        <thead class="thead-dark">
        <tr>
            <th scope="col">编号</th>
            <th scope="col">店长</th>
            <th scope="col">密码</th>
            <th scope="col">分店名</th>
            <th scope="col">操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${requestScope.userlist}" var="user">
            <tr>
                <th scope="row" class="th"><input type="checkbox" name="checkbox" class="checkbox" id="fid" value="${user.id}">${user.id}</th>
                <td>${user.userName}</td>
                <td>${user.userPsd}</td>
                <td>${user.storeName}</td>
                <td>
                    <button type="button" class="btn btn-danger" onclick="Del(this)"><a href="UserServlet?action=delete&userid=${user.id}">删除</a></button>
                    <button type="button" class="btn btn-info"><a href="UserServlet?action=getu&userid=${user.id}">修改</a></button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<div class="create" id="create">
    <div class="create_center">
        <form id="userform">
            <h2>请输入信息</h2><span id="cha">x</span>
            <label for="username">店&nbsp;&nbsp;&nbsp;&nbsp;长：</label> <input type="text" id="username" name="username" value=""><br>
            <label for="userpsd">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label> <input type="text" id="userpsd" name="userpsd" value=""><br>
            <label for="storename">分店名：</label> <input type="text" id="storename" name="storename" value=""><br>
            <button id="sure">增加</button>
        </form>
    </div>
</div>


<!-- 底部的footer -->
<div class="w-100 footer-wrapper fixed-bottom" style="height: 125px;">
    <div class="index-footer mx-auto">
        <div class="content-container clearfix hidden-xs d-flex justify-content-between">
            <div class="pull-left copy-right-text ">
                <a class="cert-link" href="https://www.meiyijia.com.cn/" target="_blank">关于美宜佳</a>
                &nbsp;&nbsp;
                <a class="cert-link" href="https://www.meiyijia.com.cn/contact/index.htm" target="_blank">联系我们</a>
                &nbsp;&nbsp;
                <a class="cert-link" href="https://www.meiyijia.com.cn/contact/index.htm" target="_blank">全国服务热线：400-887-1133</a>
                &nbsp;&nbsp;
                <br />
                <span>Copyright©2021 美宜佳股份有限公司 版权所有</span>
                &nbsp;&nbsp;&nbsp;
                <br />
                <div>
                    <a class="police-record-info cert-link" target="_blank"
                       href="https://beian.miit.gov.cn/#/Integrated/index">
                        <img class="police-img" style="float: left"
                             src="https://static.ulearning.cn/static/course_web/common/img/police_record.png" />
                        <span>粤ICP备14021486号</span>
                    </a>
                </div>
            </div>
            <div class="pull-right umooc-logo">
                <div>Powered by</div>
                <img class="logo" src="https://www.meiyijia.com.cn/images/logo.png" />
            </div>
        </div>
    </div>
</div>
</body>
<script src="js/usermanage.js"></script>

</html>