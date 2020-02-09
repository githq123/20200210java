<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String[]dept={"信息工程学院","机电工程学院","建测学院","资环学院","理学院"}; %>
<html>
<body>
<h3>教工信息录入查询</h3>
教工姓名：<input type="text" name="name" size="10">
所在学院：<select>
<%
for(int i=0;i<dept.length;i++){ %>
<option value="<%=dept[i] %>"><%=dept[i] %></option>
<%} %>
</select>
<input type="button" value="查询">
</body>
</html>