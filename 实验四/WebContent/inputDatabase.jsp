<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<jsp:useBean id="recordBean" class="mybean.data.Bean7_1" scope="session"/>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body bgcolor=pink><font size=4>
		<form action="Shiyan1_Servlet" method=post>
			<b>数据库：<input type="text" name="dataBase"size=20 value=product></b>
			<br>表名：<input type="text" name="tablename"size=23 value=product>
			<br>用户名：<input type="text" name="user" size=20 value=root>
			<br>用户密码：<input type="text"name="password" size=17.5>
			<br><input type="submit" name="b"value="提交">
		</form>
	<table border=1>
		<%	String[][]table =recordBean.getTablerecord();
		if(table==null){
			out.print("没有记录");
			return;
			}
		String[]colname=recordBean.getColname();
		if(colname!=null){
			out.print("<tr>");
			for(int i=0;i<colname.length;i++){
				out.print("<th>"+colname[i]+"</th>");
			}
			out.print("<tr>");
		}
		out.println("全部记录数"+table.length);
		for(int i=0;i<table.length;i++){
			out.print("<tr>");
			for(int j=0;j<colname.length;j++){
				out.print("<td>"+table[i][j]+"</td>");
				}
			out.print("</tr>");
		}
	%>	
	</table>
</font>
</body>
</html>
