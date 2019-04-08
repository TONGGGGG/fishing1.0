<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	 <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		<script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script> 
		<script src="https://img.hcharts.cn/highcharts/highcharts.js"></script> 
		<script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script> 
		<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script> 
</head>
<body>
   <%@include file="head.jsp" %>
   
   <div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="carousel slide" style="height: 300px" id="carousel-777562">
				<ol class="carousel-indicators">
					<li class="active" data-slide-to="0" data-target="#carousel-777562">
					</li>
					<li data-slide-to="1" data-target="#carousel-777562">
					</li>
					<li data-slide-to="2" data-target="#carousel-777562">
					</li>
				</ol>
				<div class="carousel-inner" style="height: 100%">
					<div class="item active" style="height: 100%">
						<img alt="" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/1600/500/sports/2/default.jpg" />
						<div class="carousel-caption">
							<h4>
								全天候跟踪
							</h4>
							<p>
								24小时在线小时实时监测PH，溶氧，浑浊度和水温指标，不管身在何方，开启浏览器或拿起手机就能一目了然看到水族箱里的实时水质情况。即使身在千里之外，溶氧变化也尽在掌握之中。
							</p>
						</div>
					</div>
				
					<div class="item" style="height: 100%">
						<img alt="" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/1600/500/sports/1/default.jpg" />
						<div class="carousel-caption" >
							<h4>
								NB模块
							</h4>
							<p>
								提出基于福建省最先建设的窄带物联网网络，使用NB模块及无线传感器网、信息管理、辅助决策等技术相结合的水族箱环境全天候跟踪
							</p>
						</div>
					</div>
	
					<div class="item" style="height: 100%">
						<img alt="" src="http://ibootstrap-file.b0.upaiyun.com/lorempixel.com/1600/500/sports/3/default.jpg" />
						<div class="carousel-caption">
							<h4>
								数据可视化
							</h4>
							<p>
								数据可视化(实时数据动态曲线)，异常数据报警机制，历史数据曲线展示及数据分析。根据塘口每天所测的水质数据，系统会通过分析，每天为用户生成一份水质分析日报，包含所测水质指标及用户补充水质指标的分析，为用户进行水质分析提供方案建议。
							</p>
						</div>
					</div>
				</div> <a class="left carousel-control" href="#carousel-777562" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-777562" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
   
   
    <div class="jumbotron" style="height: 220px;margin-top: 10px">
    
    	<div style="float: left;">
        <h1>Welecome</h1>
        <p></p>
        <p>
          <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath }/regist.jsp" role="button">注册设备&raquo;</a>
        </p>
       </div> 
       
    
		<div class="span12" style="height: 150px;width: 150px; float: left;margin-left: 50px">
			<img alt="140x140" style="height: 100%;width: 100%" src="../images/1.jpg" class="img-circle" />
		</div>
    </div>
</div>
</div>
</div>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> 
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>