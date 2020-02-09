<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html><body><font size=3>

<% request.setCharacterEncoding("gb2312");
String passwd=request.getParameter("passwd");
if(passwd.equals("123456"))
{
	response.sendRedirect("test.jsp");
	}
else
{   System.out.println("密码错误");
	response.sendRedirect("login.jsp");}

%>
</font></body></html>