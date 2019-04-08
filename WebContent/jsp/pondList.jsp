<%--
  Created by IntelliJ IDEA.
  User: 徐浩曈
  Date: 2019/4/7
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap垂直手风琴折叠菜单</title>

    <!--图标样式和布局-->
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">

    <!--公共样式-->
    <link rel="stylesheet" type="text/css" href="css/demo.css">

    <style type="text/css">
        .demo{padding: 2em 0; background: #fff;}
        a:hover,a:focus{
            text-decoration: none;
            outline: none;
        }
        #accordion .panel{
            border: none;
            box-shadow: none;
            border-radius: 0;
            margin: 0 0 15px 10px;
        }
        #accordion .panel-heading{
            padding: 0;
            border-radius: 30px;
        }
        #accordion .panel-title a{
            display: block;
            padding: 12px 20px 12px 50px;
            background: #ebb710;
            font-size: 18px;
            font-weight: 600;
            color: #fff;
            border: 1px solid transparent;
            border-radius: 30px;
            position: relative;
            transition: all 0.3s ease 0s;
        }
        #accordion .panel-title a.collapsed{
            background: #fff;
            color: #0d345d;
            border: 1px solid #ddd;
        }
        #accordion .panel-title a:after,
        #accordion .panel-title a.collapsed:after{
            content: "\f107";
            font-family: fontawesome;
            width: 55px;
            height: 55px;
            line-height: 55px;
            border-radius: 50%;
            background: #ebb710;
            font-size: 25px;
            color: #fff;
            text-align: center;
            border: 1px solid transparent;
            box-shadow: 0 3px 10px rgba(0, 0, 0, 0.58);
            position: absolute;
            top: -5px;
            left: -20px;
            transition: all 0.3s ease 0s;
        }
        #accordion .panel-title a.collapsed:after{
            content: "\f105";
            background: #fff;
            color: #0d345d;
            border: 1px solid #ddd;
            box-shadow: none;
        }
        #accordion .panel-body{
            padding: 20px 25px 10px 9px;
            background: transparent;
            font-size: 14px;
            color: #8c8c8c;
            line-height: 25px;
            border-top: none;
            position: relative;
        }
        #accordion .panel-body p{
            padding-left: 25px;
            border-left: 1px dashed #8c8c8c;
        }
    </style>
</head>
<body>

<div class="demo"  >
    <div class="container" >
        <div class="row" style="float: right; width: 90%; height: 1000px">
            <div class="col-md-offset-2 col-md-9">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">


                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="sdadsa">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="dfsdf">
                                    马尾区池塘#1
                                    <font style="float: right;">在线</font>
                                    <font style="float: right;">详情&nbsp;&nbsp;&nbsp;</font>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="sdasse">
                            <div class="panel-body">
                                <p>
                                    综合监测设备(22h 20m 41s前):PH:69 溶氧:<font color="red">20</font><br>
                                    独立溶氧检测器(24h 58m 21s前):溶氧:<font color="red">20</font><br>
                                </p>


                            </div>
                        </div>
                    </div>






                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwos">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwos" aria-expanded="false" aria-controls="collapseTwos" >
                                    <font color="red">This is some text!</font>
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwos" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwos">
                            <div class="panel-body">
                                <p>fdfsdfsdfsd </p>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

        <!-- <font style="float: right;">在线</font> -->

    </div>

</div>



<script type="text/javascript">
    $(function() {
        $.getJSON("pondAllDeviceChar?pid=001&beginTime=1553750979393&endTime=1554533869000" , function(data) {
            for(var p in data){
                var s = '<div id="'+p+'" style="min-width:400px;height:400px"></div>';
                $('#chart').append(s);
                var chart = Highcharts.chart(p,data[p]);
            }
        });

    });
</script>


<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>





</body>
</html>
