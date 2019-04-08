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
				
				
				
					<div class="item" style="height: 100%">
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
	
					
				</div> <a class="left carousel-control" href="#carousel-777562" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-777562" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
			
			
		<div >
			<form id="form"  method="post"  role="form" style="margin-top: 30px;">
			<div style="float: left;">
				<div class="form-group">
					 <label for="exampleInputEmail1">设备号</label><font size="2">&nbsp;&nbsp;&nbsp;(请输入购买设备时附带的唯一设备编号)</font><input name="device" value="${d.device }" type="text" style="width: 100%;" class="form-control" id="exampleInputEmail1" />
				</div>
				
				<div class="form-group">
					 <label for="exampleInputPassword1">登陆密码</label><font>&nbsp;&nbsp;&nbsp;(请输入登陆密码便于确认您的身份)</font><input name="password" type="password" style="width: 100%;" class="form-control" id="exampleInputPassword1" />
				</div>
			</div > 
				<div class="form-group" style="float: left; margin-left: 50px;">
					<div style="float: left;width: 100px;  ">
					 <label for="exampleInputEmail1">水体温度上限</label><input name="p01" value="${d.p01 }" type="text" class="form-control"  />
					</div>
					<div style="float:left;width: 100px;margin-left: 10px;">
					 <label for="exampleInputEmail1">水体PH上限</label><input  name="p11" value="${d.p11 }" type="text" class="form-control"  />
					</div>
					<div style="float: left;width: 100px;clear: left;margin-top: 15px;">
					 <label for="exampleInputEmail1">水体温度下限</label><input  name="p00" value="${d.p00 }" type="text" class="form-control"  />
					</div>
					
					<input name="method" value="upDateDevice" style="display: none;" />
						
					
					<div style="float: left;width: 100px;margin-left: 10px;margin-top: 15px;">
					 <label for="exampleInputEmail1">水体PH下限</label><input  name="p10" value="${d.p10 }" type="text" class="form-control"  />
					</div>
				</div>
				
				<div class="form-group" style="float: left; margin-left: 50px;">
					<div style="float: left;width: 100px;  ">
					 <label for="exampleInputEmail1">水体溶氧上限</label><input name="p21" value="${d.p21 }" type="text" class="form-control"  />
					</div>
					<div style="float:left;width: 100px;margin-left: 10px;">
					 <label for="exampleInputEmail1">水体浑浊度上限</label><input  name="p31" value="${d.p31 }" type="text" class="form-control"  />
					</div>
					<div style="float: left;width: 100px;clear: left;margin-top: 15px;">
					 <label for="exampleInputEmail1">水体溶氧下限</label><input  name="p20" value="${d.p20 }" type="text" class="form-control"  />
					</div>
					
					<input name="method" value="registDevice" style="display: none;" />
						
					
					<div style="float: left;width: 100px;margin-left: 10px;margin-top: 15px;">
					 <label for="exampleInputEmail1">水体浑浊度下限</label><input  name="p30" value="${d.p30 }" type="text" class="form-control"  />
					</div>
				</div>
				
				
				<div style="float: left; margin-left: 40px;margin-top: 30px;">
				<div class="checkbox">
					 <label><input type="checkbox" />同意协议</label>
				</div> 
					
					
					<div class="alert alert-success alert-dismissable" id="msgdiv" style="display: none;">
						 <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
					</div>
					<button id="sub" type='button'  style="margin-bottom: 20px;" class="btn btn-default">确定 </button>
				</div>
				
				
				</form>
				</div>
			</div>
			

		
		
		
		
		</div>
		
		
	</div>
	<script>
	
	
	$(function(){

		  $('#sub').click(function(){//点击按钮                           //要提交的表单id为form
		                   
		            $.ajax({
		                 url:"${pageContext.request.contextPath }/equip",
		                 data:$("#form").serialize(),
		                 type:"post",
		                 success:function(d){//ajax返回的数据
		            		 if(d=="修改成功~"){
		            			 $("#msgdiv").show();
		            			 $("#msgdiv").append(" <h4>恭喜!</h4><font>修改成功~</font>");
		            			 
		            		 }
		            		 else {
		            			 $("#msgdiv").show();
		            			 $("#msgdiv").append(" <h4>提示!</h4> <strong>"+d+"</strong>  ");
		                 	}
		            						}
		            });     
		        }); 


		});  

	
	</script>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> 
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>