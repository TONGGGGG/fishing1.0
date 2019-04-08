<%--
  Created by IntelliJ IDEA.
  User: 徐浩曈
  Date: 2019/4/3
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
    <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
</head>
<body>
<%--<%@include file="head.jsp" %>--%>

<div id="chart">
    <div id="container1" style="min-width:400px;height:400px"></div>
</div>
<script type="text/javascript">
    $(function() {
        $.getJSON("forecast?pid=001" , function(data) {
                var chart = Highcharts.chart("container1",data);
        });

    });
</script>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
