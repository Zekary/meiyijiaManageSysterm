<%--
  Created by IntelliJ IDEA.
  User: toumasayasu
  Date: 2021/12/9
  Time: 4:57 下午
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
    <title>修改密码</title>
</head>

<body style="overflow-x: hidden;">
<div style="margin-top: 10%; margin-left: 10%">
    <form name="resetform" id="resetform">
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="oldPassword">原密码</label>
                <input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="请输入原密码">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="newPassword">新密码</label>
                <input type="password" class="form-control" id="newPassword" name="resurePassword" placeholder="请输入新密码">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="resurePassword">确认密码</label>
                <input type="password" class="form-control" id="resurePassword" name="resurePassword" placeholder="请再次确认密码">
            </div>
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

</body>
<script src="../../js/jquery.min.js"></script>
<script>
    //合法性验证
    var submit = document.getElementById("submit");
    submit.onclick = function () {
        var oldPassword = document.getElementById("oldPassword").value;
        var newPassword = document.getElementById("newPassword").value;
        var resurePassword = document.getElementById("resurePassword").value;
        var btnreset = document.getElementById("btn-reset");
        console.log(newPassword,oldPassword,resurePassword);
        if (oldPassword == "" || newPassword == "" || resurePassword == "") {
            alert("请完善信息！");
            btnreset.onclick();
            return;
        }
        else if (newPassword !== resurePassword) {
            alert("两次输入的密码不一致！");
            btnreset.onclick();
            return;
        }
        else
        {
            alert("密码修改成功！");
            var form = document.getElementById("resetform");
            form.action = "ResetServlet?action=reset";
            form.method = "post";
            form.submit();
        }
    }
</script>

</html>
