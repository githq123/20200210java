<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html><body bgcolor=yellow><font size=3>
<%! int count;
StringBuffer personList;
public void judge () {
	if (count==0)
		personList=new StringBuffer();
}
public void addPerson(String p){
	if (count==0)
	personList.append(p);
	else
		personList.append(","+p);
	count++;
	}
%>
<% request.setCharacterEncoding("gb2312");
String name=request.getParameter("name");
if(name.length()==0 || name.length()>10){
	%> <jsp:forward page="inputName.jsp" />
	<%}
judge();
addPerson(name);
%>
<br> 目前共有<%= count %>人浏览了该页面，他们的名字是：
<br> <%= personList %>
</font></body></html>