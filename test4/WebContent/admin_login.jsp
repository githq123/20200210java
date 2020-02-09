<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>订餐系统———后台登录页</title>
<link href="EasyUI/themes/default/easyui.css" rel="stylesheet"
	type="text/css" />
<link href="EasyUI/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="EasyUI/demo.css" rel="stylesheet" type="text/css" />
<script src="EasyUI/jquery.min.js" type="text/javascript"></script>
<script src="EasyUI/jquery.easyui.min.js" type="text/javascript"></script>
<script src="EasyUI/easyui-lang-zh_CN.js" type="text/javascript"></script>
</head>

<body>
	<script type="text/javascript">
		function clearForm() {
			$('#adminLoginForm').form('clear');
		}

		function checkAdminLogin() {
			$("#adminLoginForm").form("submit", {
				url : 'admin/login',
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success == 'true') {
						window.location.href = 'index.jsp';
						$("#adminLoginDlg").dialog("close");
					} else {
						$.messager.show({
							title : "提示信息",
							msg : result.message
						});
					}
				}
			});
		}
	</script>
	<div id="adminLoginDlg" class="easyui-dialog"
		style="top: 150;left: 550;width: 250;height: 200"
		data-options="title:'后台登录',buttons:'#bb',modal:true">
		<form id="adminLoginForm" method="post">
			<table>
				<tr>
					<td>用户名</td>
					<td><input class="easyui-textbox" type="text" id="loginName"
						name="loginName" value="admin" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input class="easyui-textbox" type="text" id="loginPwd"
						name="loginPwd" value="123456" data-options="required:true"></input></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="bb">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="checkAdminLogin()">登录</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="clearForm();">重置</a>
	</div>

</body>
</html>
