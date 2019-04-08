<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
 <head>

  <script src="https://img.hcharts.cn/jquery/jquery-1.8.3.min.js"></script> 
  <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script> 
  <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script> 
  <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script> 
 </head> 
<body>
	<%@include file="head.jsp" %>
	
		<div style="margin-top: 30px;">
			<div class="container-fluid" >
				<div class="row-fluid">
					<div class="span12">
						<div class="alert" id="msgdiv" style="background: #FFF5EE;display: none;" >
							 
							<h4>
								提示!
							</h4> <strong>警告!</strong> <font id="msg"></font>
						</div>
						
					</div>
				</div>
			</div>
			
		   <div class="container-fluid" style="margin-left: 870px;" > 
			<div class="row-fluid">
				<div class="span12">
					<form class="form-horizontal" method="post" action="${pageContext.request.contextPath }/data?method=getDataPackage">
						<div class="control-group" style="float: left; margin-top: 4px">
							 						
							 <div class="controls">
								<font >设备</font>
								<select name="device">
										<c:forEach var="c" items="${user.devices }">
											<option value="${c.device }" >${c.device }</option>
										</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group" style="float: left; margin-left: 20px;">
							 	
							<div class="controls">
								<font>日期&nbsp;</font><input type="date" value="2018-04-19" name="time"/>
							</div>
						</div>
						<div class="control-group" style="float: left;margin-left: 20px;">
							<div class="controls">
								  <button  type="submit" class="btn">查询</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		
		<div id="container1" style="min-width:400px;height:400px"></div>
		
		
		</div>	
		
     <script>
     
     
     var a =1;//调用HightChar类库的方法（该方法用于填充指定id的div）：
	//传参数给该方法：1.元素ID（'container1'）  
	//			2.以及填充该div所需要的：指定的图表类型，图表标题，x轴名称(再次类型图表中为数组)，y轴名称，对应的几组数据
     var chart = Highcharts.chart('container1', {
    	    chart: {
    	        type: 'line'
    	    },
    	    title: {
    	        text: ${dp.title}
    	    },
    	    subtitle: {
    	        text: '数据来源: Fishing1.0         (24小时全天侯监控)'
    	    },
    	    xAxis: {
    	        categories: ${dp.time}
    	    },
    	    yAxis: {
    	        title: {
    	            text: '值'
    	        }
    	    },
    	    plotOptions: {
    	        line: {
    	            dataLabels: {
    	                enabled: true          // 开启数据标签
    	            },
    	            enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
    	        }
    	    },
    	    series: [{
    	        name: 'PH',
    	        data: ${dp.p0}
    	    }, {
    	        name: '温度',
    	        data: ${dp.p1}
    	    }, {
    	        name: '溶氧',
    	        data: ${dp.p2}
    	    }, {
    	        name: '浑浊度',
    	        data: ${dp.p3}
    	    }]
    	});

  		   
     
 






		</script>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> 
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>