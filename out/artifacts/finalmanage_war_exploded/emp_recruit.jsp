<%--
  Created by IntelliJ IDEA.
  User: toumasayasu
  Date: 2021/12/21
  Time: 5:52 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/footer.css">
    <title>招聘信息</title>
    <style>
        label {
            margin-left: 0px;
        }
    </style>
</head>
<body style="margin-left: 4%; overflow:Scroll;overflow-x:hidden">
<div style="width: 40%; height: 50%;">
    <form>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputEmail4">招聘职位</label>
                <input type="email" class="form-control" id="inputEmail4" placeholder="请输入招聘职位">
            </div>
            <div class="form-group col-md-6">
                <label for="inputPassword4">薪资</label>
                <input type="password" class="form-control" id="inputPassword4" placeholder="请输入薪资">
            </div>
        </div>
        <div class="form-group">
            <label for="inputAddress">学历要求</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="请输入学历要求">
        </div>
        <div class="form-group">
            <label for="inputAddress2">待遇</label>
            <input type="text" class="form-control" id="inputAddress2" placeholder="请输入待遇">
        </div>

        <div class="form-group">
            <label for="inputCity">分店地址</label>
            <input type="text" class="form-control" id="inputCity" placeholder="请输入分店地址">
        </div>

        <div class="form-row">
            <label for="exampleFormControlTextarea1">职位描述</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
        </div>
        <br>
        <button type="submit" class="btn btn-primary" id="submit">发布招聘信息</button>
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
                <a class="cert-link" href="http://help.ulearning.cn/" target="_blank">全国服务热线：400-887-1133</a>
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

<script>
    var sub = document.getElementById("submit");
    sub.onclick = function () {
        alert("招聘信息发布成功!");
    }
</script>
</html>
