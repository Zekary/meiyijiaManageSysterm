<%--
  Created by IntelliJ IDEA.
  User: toumasayasu
  Date: 2021/12/17
  Time: 8:58 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>店长修改</title>
    <!-- 引入bootstrap.css 和 footer.css  -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/footer.css">
</head>
<body style="overflow-x: hidden;">
<div style="margin-top: 10%; margin-left: 10%">
    <form name="uupdateform" action="UserServlet?action=update&userid=${requestScope.user.id}" method="post">
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="username">店长</label>
                <input type="text" class="form-control" id="username" name="username" value="${requestScope.user.userName}">
            </div>

            <div class="form-group col-md-4">
                <label for="psd">密码</label>
                <input type="text" class="form-control" id="psd" name="psd" value="${requestScope.user.userPsd}">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="storename">分店名</label>
                <input type="text" class="form-control" id="storename" name="storename" value="${requestScope.user.storeName}">
            </div>
            <div class="form-group col-md-4" style="margin-top: 31px; margin-left: 2%;">
                <button type="submit" class="btn btn-outline-success">提交</button>
                <button type="button" class="btn btn-outline-primary" id="btn-reset" onclick="location.href=('UserServlet?action=list')">取消</button>
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
                <br/>
                <span>Copyright©2021 美宜佳股份有限公司 版权所有</span>
                &nbsp;&nbsp;&nbsp;
                <br/>
                <div>
                    <a class="police-record-info cert-link" target="_blank"
                       href="https://beian.miit.gov.cn/#/Integrated/index">
                        <img class="police-img" style="float: left"
                             src="https://static.ulearning.cn/static/course_web/common/img/police_record.png"/>
                        <span>粤ICP备14021486号</span>
                    </a>
                </div>
            </div>
            <div class="pull-right umooc-logo">
                <div>Powered by</div>
                <img class="logo" src="https://www.meiyijia.com.cn/images/logo.png"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>
