<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	if (session.getAttribute("admininfo") == null)
		response.sendRedirect("/restaurant-back/admin_login.jsp");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>后台管理首页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="EasyUI/themes/default/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="EasyUI/demo.css" rel="stylesheet" type="text/css" />
<script src="EasyUI/jquery.min.js" type="text/javascript"></script>
<script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
<script src="EasyUI/easyui-lang-zh_CN.js" type="text/javascript"></script>

</head>

<body class="easyui-layout">

	<div data-options="region:'north',border:false"
		style="height:60px;background:#B3DFDA;padding:10px">
		<div align="left">
			<font style="font-family: cursive;;font-size: 20">网上订餐系统后台管理</font>
		</div>
		<div align="right">欢迎您，${sessionScope.admininfo.loginName}</div>
	</div>
	<div data-options="region:'west',split:true,title:'功能菜单'"
		style="width:200px;padding:10px;">
		<!-- 定义ul -->
		<ul id="tt"></ul>
	</div>
	<div data-options="region:'south',border:false"
		style="height:50px;background:#A9FACD;padding:10px;" align="center">Powered
		By MiaoYong</div>
	<div data-options="region:'center',title:'主界面'">
		<div id="tabs" data-options="fit:true" class="easyui-tabs"
			style="width:500px;height:250px"></div>
	</div>
	
	<script type="text/javascript">
		// 为tree指定数据
		$('#tt').tree({
			url : 'admin/getTree?adminid=${sessionScope.admininfo.id}'
		});
		$('#tt').tree({
			onClick : function(node) {
				if ("餐品列表" == node.text) {
					if ($('#tabs').tabs('exists', '餐品列表')) {
						$('#tabs').tabs('select', '餐品列表');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'meallist.jsp',
							closable : true
						});
					}

				} else if ("查询订单" == node.text) {
					if ($('#tabs').tabs('exists', '查询订单')) {
						$('#tabs').tabs('select', '查询订单');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'searchorder.jsp',
							closable : true
						});
					}
				} else if ("创建订单" == node.text) {
					if ($('#tabs').tabs('exists', '创建订单')) {
						$('#tabs').tabs('select', '创建订单');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'createorder.jsp',
							closable : true
						});
					}
				} else if ("订单统计" == node.text) {
					if ($('#tabs').tabs('exists', '订单统计')) {
						$('#tabs').tabs('select', '订单统计');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'saler.jsp',
							closable : true
						});
					}
				} else if ("用户列表" == node.text) {
					if ($('#tabs').tabs('exists', '用户列表')) {
						$('#tabs').tabs('select', '用户列表');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'userlist.jsp',
							closable : true
						});
					}
				} else if ("管理员列表" == node.text) {
					if ($('#tabs').tabs('exists', '管理员列表')) {
						$('#tabs').tabs('select', '管理员列表');
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							href : 'adminlist.jsp',
							closable : true
						});
					}
				} else if ("退出系统" == node.text) {
					$.ajax({
						url : 'admin/loginout',
						success : function(data) {
							window.location.href = "admin_login.jsp";
						}
					})
				}
			}
		});
	</script>
</body>
</html>
