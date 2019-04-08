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
							设备编号
						</th>
						<th>
							产品名
						</th>
						<th>
							注册时间
						</th>
						<th>
							温度上下限
						</th>
						<th>
							PH上下限
						</th>
						<th>
							氧浓度上下限
						</th>
						<th>
							浑浊度上下限
						</th>
						<th>
							更改
						</th>
						<th>
							注销设备
						</th>
					</tr>
				</thead>
				<tbody >
					
					<c:forEach items="${list}" var="d" varStatus="i">
						<c:if test="${i.count%2==0 }">
						<tr class="info">
						<td>
							${d.device }
						</td>
						<td>
							FIshing1.0
						</td>
						<td>
							01/04/2012
						</td>
						<td>
							${d.p00 }-${d.p01 }
						</td>
						<td>
							${d.p10 }-${d.p11 }
						</td>
						<td>
							${d.p20 }-${d.p21 }
						</td>
						<td>
							${d.p30 }-${d.p31 }
						</td>
						<td>
							<a href="${pageContext.request.contextPath }/equip?method=getDevice&device=${d.device }">${d.device }</a>
						</td>
						<td>
							<a href="${pageContext.request.contextPath }/equip?method=deletDevice&device=${d.device }">注销</a>
						</td>
					</tr>
					</c:if>
					
					<c:if test="${i.count%2==1 }">
						<tr class="warning">
						<td>
							${d.device }
						</td>
						<td>
							FIshing1.0
						</td>
						<td>
							01/04/2012
						</td>
						<td>
							${d.p00 }-${d.p01 }
						</td>
						<td>
							${d.p10 }-${d.p11 }
						</td>
						<td>
							${d.p20 }-${d.p21 }
						</td>
						<td>
							${d.p30 }-${d.p31 }
						</td>
						<td>
							<a href="${pageContext.request.contextPath }/equip?method=getDevice&device=${d.device }">${d.device }</a>
						</td>
						<td>
							<a href="${pageContext.request.contextPath }/equip?method=deletDevice&device=${d.device }">注销</a>
						</td>
					</tr>
					</c:if>
					
					
					
					
					</c:forEach>
					
					
					
				</tbody>
			</table>
		</div>
	</div>
</div>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> 
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>