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
    
    <title>注册页面</title>
    
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
    	<h3><font color="blue">填写注册信息</font></h3>
    	<font color="red" size="3px"> <s:fielderror /> </font>
    	<s:form name="form1" action="register" method="post">
    		<s:textfield name="user.loginName" label="登录名称"></s:textfield>
    		<s:password name="user.loginPwd" label="登录密码"/>
    		<s:password name="repassword" label="确认密码"/>
    		<s:textfield name="user.trueName" label="真实姓名"/>
    		<s:textfield name="user.email" label="电子邮件" />
    		<s:textfield name="user.phone" label="联系电话" />
    		<s:textfield name="user.address" label="联系地址" />
    		<s:submit value="注册"></s:submit>
    	</s:form>
  </body>
</html>
