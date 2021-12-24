<%--
  Created by IntelliJ IDEA.
  User: toumasayasu
  Date: 2021/12/16
  Time: 11:20 下午
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
    <title>财务图表</title>

</head>
<body style="margin-left: 4%; overflow-y:Scroll;overflow-x:hidden" onLoad="javascript:auto();">
<div id="main" style="height: 70%; width: 80%; margin-left: 5%; margin-top: 5%;"></div>
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
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script>
    function auto() {
        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);

        // 基于准备好的dom，初始化echarts实例
        var monthData=[];
        var incomeData=[];
        var expendData=[];
        var profitData=[];
        <c:forEach items="${requestScope.financiallist}" var="finan">
        monthData.push("${finan.month}");  //JS里面传递String要加""！！！！
        incomeData.push(${finan.income});
        expendData.push(${finan.expend});
        profitData.push(${finan.profict});
        </c:forEach>
        var option;
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: { show: true, readOnly: false },
                    magicType: { show: true, type: ['line', 'bar'] },
                    restore: { show: true },
                    saveAsImage: { show: true }
                }
            },
            legend: {
                data: ['营业额', '总支出', '净利润']
            },
            xAxis: [
                {
                    type: 'category',
                    data: monthData,
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '营业额',
                    min: 0,
                    max: 3000,
                    interval: 500,
                    axisLabel: {
                        formatter: '{value} 元'
                    }
                },
                {
                    type: 'value',
                    name: '总支出',
                    min: 0,
                    max: 3000,
                    interval: 500,
                    axisLabel: {
                        formatter: '{value} 元'
                    }
                }
            ],
            series: [
                {
                    name: '营业额',
                    type: 'bar',
                    data: incomeData
                },
                {
                    name: '总支出',
                    type: 'bar',
                    data: expendData
                },
                {
                    name: '净利润',
                    type: 'line',
                    yAxisIndex: 1,
                    data: profitData
                }
            ]
        };
        option && myChart.setOption(option);
        console.log(echarts);
    }
</script>
</body>
</html>

