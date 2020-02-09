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
    
    <title>My JSP 'reg.jsp' starting page</title>
    
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
    <h3>信息录入</h3>
    <s:form action="reg">
    	<s:textfield name="name" label="名称"></s:textfield>
    	<s:textfield name="age" label="年龄"></s:textfield>
    	<s:textfield name="birthday" label="生日"/>
    	<s:textfield name="point" label="坐标"/>
    	<s:checkboxlist label="爱好" name="hobby" list="{'读书','跳舞','游泳','唱歌'}" value="{'读书','唱歌'}"/>
    	<s:submit value="提交"></s:submit>
    	<s:reset value="重置"/>
    </s:form>
  </body>
</html>
