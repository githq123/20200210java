<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册成功页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <h3><font color="blue">用户注册的详细信息</font></h3>
    	登录名称：<s:property value="user.loginName"/><br>
    	登录密码：<s:property value="user.loginPwd"/><br>
    	真实姓名：<s:property value="user.trueName"/><br>
    	电子邮件：<s:property value="user.email"/><br>
    	联系电话：<s:property value="user.phone"/><br>
    	联系地址：<s:property value="user.address"/><br>
    
  </body>
</html>
