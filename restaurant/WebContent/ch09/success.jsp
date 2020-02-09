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
    
    <title>My JSP 'success.jsp' starting page</title>
    
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
   		<h3>注册信息如下：</h3>
   		名称：<s:property value="name"/><br>
		年龄：<s:property value="age"/><br>
		生日：<s:property value="birthday"/><br>
		X坐标：<s:property value="point.x"/><br>
		Y坐标：<s:property value="point.y"/><br>
		兴趣爱好：
		<s:iterator value="#request.hobby" var="v">
			<s:property value="v"/>&nbsp;
		</s:iterator>
  </body>
</html>
