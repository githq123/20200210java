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
    
    <title>My JSP 'showOgnl.jsp' starting page</title>
    
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
      <h3>构造Map</h3>
      <s:set var="foobar" value="#{'foo1':'bar1', 'foo2':'bar2'}"></s:set>
      <p>The value of key "foo1" is <s:property value="#foobar['foo1']"/></p>
      <p>不使用%：<s:url value="#foobar['foo1']"></s:url> </p>
      <p>使用%：<s:url value="%{#foobar['foo1']}"/> </p>
  </body>
</html>
