<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String[]dept={"��Ϣ����ѧԺ","���繤��ѧԺ","����ѧԺ","�ʻ�ѧԺ","��ѧԺ"}; %>
<html>
<body>
<h3>�̹���Ϣ¼���ѯ</h3>
�̹�������<input type="text" name="name" size="10">
����ѧԺ��<select>
<%
for(int i=0;i<dept.length;i++){ %>
<option value="<%=dept[i] %>"><%=dept[i] %></option>
<%} %>
</select>
<input type="button" value="��ѯ">
</body>
</html>