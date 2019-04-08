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
   
   <div class="container-fluid" style="margin-top: 30px;">
	<div class="row-fluid">
		<div class="span12">
			<table class="table table-hover">
				<thead >
					<tr >
						<th>
							物种名
						</th>
						<th>
							种类
						</th>
						<th>
							水质
						</th>
						<th>
							适应温度
						</th>
						<th>
							适应PH
						</th>
						<th>
							适应氧浓度
						</th>
						<th>
							适应浑浊度
						</th>
						<th>
							更改
						</th>
						<th>
							删除
						</th>
					</tr>
				</thead>
				<tbody >
					
						<tr class="info">
						<td>
							血鹦鹉
						</td>
						<td>
							鱼类
						</td>
						<td>
							淡水
						</td>
						<td>
							2-33
						</td>
						<td>
							12-14
						</td>
						<td>
							14-15
						</td>
						<td>
							15-16
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					<tr class="warning">
						<td>
							龙鱼
						</td>
						<td>
							鱼类
						</td>
						<td>
							淡水
						</td>
						<td>
							11-19
						</td>
						<td>
							1-15
						</td>
						<td>
							14-30
						</td>
						<td>
							9-88
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					<tr class="info">
						<td>
							樱花虾
						</td>
						<td>
							甲壳类
						</td>
						<td>
							淡水
						</td>
						<td>
							2-14
						</td>
						<td>
							5-33
						</td>
						<td>
							5-32
						</td>
						<td>
							24-27
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					<tr class="warning">
						<td>
							黑壳虾
						</td>
						<td>
							甲壳类
						</td>
						<td>
							淡水
						</td>
						<td>
							16-24
						</td>
						<td>
							17-34
						</td>
						<td>
							14-15
						</td>
						<td>
							15-18
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					<tr class="info">
						<td>
							安南龟
						</td>
						<td>
							龟类
						</td>
						<td>
							两栖
						</td>
						<td>
							10-14
						</td>
						<td>
							13-17
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					
					<tr class="warning">
						<td>
							虎鱼
						</td>
						<td>
							鱼类
						</td>
						<td>
							淡水
						</td>
						<td>
							61-81
						</td>
						<td>
							21-31
						</td>
						<td>
							41-66
						</td>
						<td>
							1-11
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					
					
					<tr class="info">
						<td>
							魟鱼
						</td>
						<td>
							鱼类
						</td>
						<td>
							淡水
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					
					<tr class="warning">
						<td>
							招财鱼
						</td>
						<td>
							鱼类
						</td>
						<td>
							淡水
						</td>
						<td>
							1-43
						</td>
						<td>
							12-23
						</td>
						<td>
							13-34
						</td>
						<td>
							12-34
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					
					<tr class="info">
						<td>
							地图鱼
						</td>
						<td>
							鱼类
						</td>
						<td>
							淡水
						</td>
						<td>
							13-19
						</td>
						<td>
							1-13
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					
					
					<tr class="warning">
						<td>
							凹甲陆龟
						</td>
						<td>
							龟类
						</td>
						<td>
							淡水
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					
					<tr class="info">
						<td>
							澳洲蛇颈龟
						</td>
						<td>
							龟类
						</td>
						<td>
							淡水
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							1-11
						</td>
						<td>
							<a href="/equip?method=getDevice&device">更改</a>
						</td>
						<td>
							<a href="/equip?method=deletDevice&device">删除</a>
						</td>
					</tr>
					
					
					
				</tbody>
			</table>
		</div>
	</div>
</div>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> 
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>