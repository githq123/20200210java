<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function register(){
			var form=document.forms[0];
			form.action="lrAction!register";
			form.submit();
		}
	</script>
  </head>
  
  <body>
     <h3>用户登录</h3>
     <!-- 动态方法调用
      <form name="form1" method="post" action="lrAction!login">  
  	        用户名：<input type="text" name="user.loginName"> <br><br>
  	        密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="user.loginPwd"> <br> <br>
  	   <input type="submit" value="登录">
  	   <input type="button" value="注册" onclick="register()">
	  </form>
	 -->  
	 <!-- 使用method属性处理调用方法 -->
	  <form name="form1" method="post" action="loginAction">  
  	        用户名：<input type="text" name="user.loginName"> <br><br>
  	        密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="user.loginPwd"> <br> <br>
  	   <input type="submit" value="登录">
  	   <input type="button" value="注册" onclick="javascript:window.location.href='registerAction'">
	  </form>
  </body>
</html>
