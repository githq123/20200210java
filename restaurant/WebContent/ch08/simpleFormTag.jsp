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
    
    <title>My JSP 'simpleFormTag.jsp' starting page</title>
    
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
    <center>
    	<h3>注册登记表</h3>
    	<s:form action="register" method="post">
    		<s:textfield name="loginName" label="姓名"></s:textfield>
    		<s:password name="loginPwd" label="口令"/>
    		<s:select name="degree" label="学历"
    		list="{'高中及以下','大学专科','大学本科','研究生及以上'}" />
    		<s:radio name="sex" label="性别" list="{'男','女'}"></s:radio>
    		<s:textarea name="protocol" label="注册协议" value="这里省略协议"/>
    		<s:checkbox name="love" label="同意员工登记协议"/>
    		<s:submit value="提交"></s:submit>
    		<s:reset value="重置"/>
    	</s:form>
    </center>
  </body>
</html>
