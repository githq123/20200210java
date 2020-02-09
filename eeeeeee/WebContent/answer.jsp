<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html><body><font size=3>

<%
request.setCharacterEncoding("gb2312");
String answer=request.getParameter("option");
if(answer==null)
{System.out.println("请选择！");}
else if(answer.equals("A"))
{System.out.println("恭喜答对了！");
response.sendRedirect("login.jsp");}
else
{System.out.println("很遗憾，答错了!");
response.sendRedirect("test.jsp");}
%>
</font></body></html>