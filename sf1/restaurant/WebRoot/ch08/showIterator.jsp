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
    
    <title>控制标签示例</title>
    
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
   	<s:iterator value="{'故人西辞黄鹤楼','烟花三月下扬州'}" var="poem">
   		<s:property value="poem"/><br>
   	</s:iterator>
   	<hr>
   	<s:iterator value="#{'1001':'Java程序设计','1002':'JSP程序设计','1003':'SSH框架技术'}" var="bookName">
   		<s:property value="key"/>
   		<s:property value="value"/>
   		<br>
   	</s:iterator>
   	<hr>
    <s:iterator value="{'清华大学','复旦大学','北京大学','南京大学'}" var="university" status="stat">
   		<s:if test="#stat.odd">  <!-- 判断当前索引是否为奇数 -->
   			<s:property value="#stat.count"/>&nbsp;<s:property/><br>
   		</s:if>   		
   	</s:iterator>
   	<hr>
   	<table border="1">
   		<tr>
   			<td>序号</td>
   			<td>出版社</td>
   		</tr>
   		<s:iterator value="{'清华大学出版社','人民邮电出版社','北京大学出版社','电子工业出版社'}" var="publisher" status="stat">
      	<tr>
      	  <s:if test="#stat.index%2==0">
   			<td><s:property value="#stat.count"/> </td>
   			<td style="background-color: red;">
   				<s:property value="publisher"/>
   			</td>
   		  </s:if>
   		  <s:else>
   		    <td><s:property value="#stat.count"/> </td>
   			<td style="background-color: gray;">
   				<s:property value="publisher"/>
   			</td>
   		  </s:else>
   		</tr>
   
   		</s:iterator>
   	</table>
  </body>
</html>
