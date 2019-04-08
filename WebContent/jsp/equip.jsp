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
   <div id="container1" style="width:50%;height:250px;float:left"></div> 
   <div id="container2" style="width:50%;height:250px;float:left"></div> 
   <div id="container3" style="width:50%;height:250px;float:left"></div> 
   <div id="container4" style="width:50%;height:250px;float:left"></div> 
     <script>
     
     
     var tp;
     var hz;
     var ox;
     var ph;
     $(document).ready(function() {   
              //每隔1秒自动调用方法，实现图表的实时更新   
    	 window.setInterval(getData,5000);                 
     });   
     function getData(){  
    	 $.get("${pageContext.request.contextPath }/getData","eid=${param.eid}",function(d){
    		 if(d!=null){
    		 tp=d.tp;
    		 hz=d.hz;
    	     ox=d.ox;
    		 ph=d.ph;}
    	 },"json");
     }

     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
			Highcharts.setOptions({
    		global: {
        		useUTC: false
    				}
									});
function activeLastPointToolip(chart) {
    		var points = chart.series[0].points;
    		chart.tooltip.refresh(points[points.length -1]);
									}
$('#container1').highcharts({
    chart: {
        type: 'spline',
        animation: Highcharts.svg, // don't animate in old IE
        marginRight: 10,
        events: {
            load: function () {
                // set up the updating of the chart each second
                var series = this.series[0],
                    chart = this;
                setInterval(function () {
                    var x = (new Date()).getTime(), // current time
                        y = ox;
                    series.addPoint([x, y], true, true);
                    activeLastPointToolip(chart)
                }, 5000);
            }
        }
    },
    title: {
        text: '氧浓度'
    },
    xAxis: {
        type: 'datetime',
        tickPixelInterval: 150
    },
    yAxis: {
        title: {
            text: '值'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    },
    tooltip: {
        formatter: function () {
            return '<b>' + this.series.name + '</b><br/>' +
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                Highcharts.numberFormat(this.y, 2);
        }
    },
    legend: {
        enabled: false
    },
    exporting: {
        enabled: false
    },
    series: [{
        name: '实时数据',
        data: (function () {
            // generate an array of random data
            var data = [],
                time = (new Date()).getTime(),
                i;
            for (i = -19; i <= 0; i += 1) {
                data.push({
                    x: time + i * 1000,
                    y: 0
                });
            }
            return data;
        }())
    }]
}, function(c) {
    activeLastPointToolip(c)
});


Highcharts.setOptions({
	global: {
		useUTC: false
			}
							});
function activeLastPointToolip(chart) {
	var points = chart.series[0].points;
	chart.tooltip.refresh(points[points.length -1]);
							}
$('#container2').highcharts({
chart: {
type: 'spline',
animation: Highcharts.svg, // don't animate in old IE
marginRight: 10,
events: {
    load: function () {
        // set up the updating of the chart each second
        var series = this.series[0],
            chart = this;
        setInterval(function () {
            var x = (new Date()).getTime(), // current time
                y = ph;
            series.addPoint([x, y], true, true);
            activeLastPointToolip(chart)
        }, 5000);
    }
}
},
title: {
text: 'PH'
},
xAxis: {
type: 'datetime',
tickPixelInterval: 150
},
yAxis: {
title: {
    text: '值'
},
plotLines: [{
    value: 0,
    width: 1,
    color: '#808080'
}]
},
tooltip: {
formatter: function () {
    return '<b>' + this.series.name + '</b><br/>' +
        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
        Highcharts.numberFormat(this.y, 2);
}
},
legend: {
enabled: false
},
exporting: {
enabled: false
},
series: [{
name: '实时数据',
data: (function () {
    // generate an array of random data
    var data = [],
        time = (new Date()).getTime(),
        i;
    for (i = -19; i <= 0; i += 1) {
        data.push({
            x: time + i * 1000,
            y: 0
        });
    }
    return data;
}())
}]
}, function(c) {
activeLastPointToolip(c)
});







Highcharts.setOptions({
	global: {
		useUTC: false
			}
							});
function activeLastPointToolip(chart) {
	var points = chart.series[0].points;
	chart.tooltip.refresh(points[points.length -1]);
							}
$('#container3').highcharts({
chart: {
type: 'spline',
animation: Highcharts.svg, // don't animate in old IE
marginRight: 10,
events: {
    load: function () {
        // set up the updating of the chart each second
        var series = this.series[0],
            chart = this;
        setInterval(function () {
            var x = (new Date()).getTime(), // current time
                y = tp;
            series.addPoint([x, y], true, true);
            activeLastPointToolip(chart)
        }, 5000);
    }
}
},
title: {
text: '温度'
},
xAxis: {
type: 'datetime',
tickPixelInterval: 150
},
yAxis: {
title: {
    text: '值'
},
plotLines: [{
    value: 0,
    width: 1,
    color: '#808080'
}]
},
tooltip: {
formatter: function () {
    return '<b>' + this.series.name + '</b><br/>' +
        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
        Highcharts.numberFormat(this.y, 2);
}
},
legend: {
enabled: false
},
exporting: {
enabled: false
},
series: [{
name: '实时数据',
data: (function () {
    // generate an array of random data
    var data = [],
        time = (new Date()).getTime(),
        i;
    for (i = -19; i <= 0; i += 1) {
        data.push({
            x: time + i * 1000,
            y: 0
        });
    }
    return data;
}())
}]
}, function(c) {
activeLastPointToolip(c)
});










Highcharts.setOptions({
	global: {
		useUTC: false
			}
							});
function activeLastPointToolip(chart) {
	var points = chart.series[0].points;
	chart.tooltip.refresh(points[points.length -1]);
							}
$('#container4').highcharts({
chart: {
type: 'spline',
animation: Highcharts.svg, // don't animate in old IE
marginRight: 10,
events: {
    load: function () {
        // set up the updating of the chart each second
        var series = this.series[0],
            chart = this;
        setInterval(function () {
            var x = (new Date()).getTime(), // current time
                y = hz;
            series.addPoint([x, y], true, true);
            activeLastPointToolip(chart)
        }, 5000);
    }
}
},
title: {
text: '浑浊度'
},
xAxis: {
type: 'datetime',
tickPixelInterval: 150
},
yAxis: {
title: {
    text: '值'
},
plotLines: [{
    value: 0,
    width: 1,
    color: '#808080'
}]
},
tooltip: {
formatter: function () {
    return '<b>' + this.series.name + '</b><br/>' +
        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
        Highcharts.numberFormat(this.y, 2);
}
},
legend: {
enabled: false
},
exporting: {
enabled: false
},
series: [{
name: '实时数据',
data: (function () {
    // generate an array of random data
    var data = [],
        time = (new Date()).getTime(),
        i;
    for (i = -19; i <= 0; i += 1) {
        data.push({
            x: time + i * 1000,
            y: 0
        });
    }
    return data;
}())
}]
}, function(c) {
activeLastPointToolip(c)
});






		</script>
  <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> 
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>