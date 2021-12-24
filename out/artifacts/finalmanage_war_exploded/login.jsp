<%--
  Created by IntelliJ IDEA.
  User: toumasayasu
  Date: 2021/11/28
  Time: 8:21 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
  <meta charset="UTF-8">
  <title>美宜佳登录界面</title>

  <link rel="stylesheet" href="css/font-awesome-4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="css/style.css">

  <script>
    //判断用户名和密码是否为空
    function checkvalid()
    {
      var strmg=login1.name.value;
      var pass=login1.pass.value;
      if(strmg=="" ||  pass=="")
      {
        alert("用户名或密码为空,请输入用户名和密码。（若无账号密码请先联系超级管理员！）");
        window.location.href='login.jsp';
        //login.pwd.value=" ";
        return false;
      }
      else
        return true;
    }
  </script>

</head>

<body>

<div class="materialContainer">
  <div class="box">
    <img class="logo" src="https://www.meiyijia.com.cn/images/logo.png"/>
    <form  name="login1" action="LoginServlet" method="post" >
      <div class="title">美宜佳门店管理系统</div>
      <div class="input">
        <label for="name">用户名</label>
        <input type="text" name="name" id="name">
        <span class="spin"></span>
      </div>
      <div class="input">
        <label for="pass">密码</label>
        <input type="password" name="pass" id="pass">
        <span class="spin"></span>
      </div>

      <div class="button login">
        <button onclick="return checkvalid()">
          <span>登录</span>
          <i class="fa fa-check"></i>
        </button>
      </div>
    </form>
    <%--        <a href="javascript:" class="pass-forgot">忘记密码？</a>--%>
  </div>
  <h2 style="text-align: center">@版权所有：美宜佳有限公司</h2>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/index.js"></script>

</body>

</html>
