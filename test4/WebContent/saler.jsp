<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'saler.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<div id="main" style="width:600px;height:400px;margin: 20px;"></div>
	<script
		src="${pageContext.request.contextPath}/echarts-master/build/dist/echarts.js"></script>
	<script type="text/javascript">
		setTimeout(DayNumOfMonth, 1000);
		function DayNumOfMonth() {
			$
			.ajax({
				url : "order/salerStatistics",
				type : "post",
				async : false,
				datatype : "text",
				success : function(data) {
					// alert(data);
				    mealNameArr = data[0]; 
				    accountArr = data[1];
					require([ 'echarts', 'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
					], function(ec) {
						// 基于准备好的dom，初始化echarts图表
						var myChart = ec.init(document
								.getElementById('main'));
						//设置数据
						var option = {
							title : {
								text : '销售统计'
							},
							tooltip : {},
							legend : {
								data : [ '销售总额' ]
							},
							//设置坐标轴
							xAxis : [ {
								type : 'category',											
								data : mealNameArr
							} ],
							yAxis : [ {
								type : 'value'
							} ],
							//设置数据
							series : [ {
								"name" : "销售总额",
								"type" : "bar",
								data : accountArr		
										
							} ]
						};
						// 为echarts对象加载数据 
						myChart.setOption(option);
					})
				}
			});
		}
	</script>
</body>
</html>
