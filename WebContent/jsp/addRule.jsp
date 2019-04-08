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
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form>
				<fieldset>
					 <legend>系统新增规则</legend>
					 
					  <label>名称：</label>
					  <input type="text" /> 
					  <span class="help-block">这里填写水产品的名称.</span> 
					  
					  
					  <label>类别：</label>
					   <select name="">
							<option value="" selected>鱼类</option>
							<option value="">甲壳类</option>
							<option value="">贝类</option>
							<option value="">藻类</option>
							<option value="">其他</option>
					   </select>
					  <span class="help-block">这里填写水产品种类.</span> 
					 
					  
					  <label>水质：</label>
					  海水<input type="radio" name="water" >
					  淡水<input type="radio" name="water" >
					  <span class="help-block">这里填写海水/淡水.</span> 
					  
					  <label>简介：</label>
					  <textarea name="" rows="" cols=""></textarea>
					  <span class="help-block">这里填写该水产品信息.</span>
					  
					  
					  <label>最佳温度：</label>
					  <input type="number" name="points" min="1" max="10" step="0.1" />
					  <span class="help-block">这里填写该水产品养殖环境的最佳温度.</span>
					  
					  <label>最佳PH：</label>
					  <input type="number" name="points" min="1" max="10" step="0.1" />
					  <span class="help-block">这里填写该水产品养殖环境的最佳PH.</span>
					  
					  
					  <input type="checkbox" /> 勾选同意</label> 
					  <button type="submit" class="btn">提交</button>
				</fieldset>
			</form>
		</div>
	</div>
</div>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> 
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>