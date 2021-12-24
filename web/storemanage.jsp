<%--
  Created by IntelliJ IDEA.
  User: toumasayasu
  Date: 2021/12/17
  Time: 6:26 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://lib.sinaapp.com/js/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/footer.css">
    <title>分店管理</title>
</head>

<body style="overflow-x: hidden;">
<div style="margin-top: 10%; margin-left: 10%">
    <form name="resetform" id="resetform">
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="username">用户名</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入账户">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="password">密码</label>
                <input type="text" class="form-control" id="password" name="password" placeholder="请输入对应的密码">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-4" style="margin-top: 31px; margin-left: 2%;">
                <button type="submit" class="btn btn-outline-success" id="submit">提交</button>
                <button type="reset" class="btn btn-outline-primary" id="btn-reset">重置</button>
            </div>
        </div>
    </form>
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

<script src="../../js/jquery.min.js"></script>
<script>
    //合法性验证
    var submit = document.getElementById("submit");
    submit.onclick = function () {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var btnreset = document.getElementById("btn-reset");
        if (username=="" || password=="") {
            alert("请完善信息！");
            btnreset.onclick();
            return;
        }
        else
        {
            alert("访问成功！");
            window.open("LoginServlet?name="+username+"&pass=" + password);
        }
    }
</script>

</body>

</html>