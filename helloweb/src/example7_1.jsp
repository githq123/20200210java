<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%> 
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Connection con;
Statement sql;
ResultSet rs;
try{ Class.forName("com.mysql.jdbc.Driver");
}
catch(Exception e){
out.println("���ǰ�MYSQL���ݿ��JDBC���ݿ����������Ƶ�JDK����չĿ¼��");
}
try{String uri="jdbc:mysql://127.0.0.1/warhouse";
String user="root";
String password="";
con=DriverManager.getConnection(uri+"?user=root&password=");
sql=con.createStatement();
rs=sql.executeQuery("SELECT * FROM product ");
out.println("<table border=2>");
out.println("<tr>");
out.print("<th width=100>"+"��Ʒ��");
out.print("<th width=100>"+"����");
out.print("<th width=50>"+"��������");
out.print("<th width=50>"+"�۸�");
out.print("</TR>");
while(rs.next()){
out.println("<tr>");
out.print("<td>"+rs.getString(1)+"</td>");
out.print("<td>"+rs.getString(2)+"</td>");
out.print("<td>"+rs.getDate("madeTime")+"</td>");
out.print("<td>"+rs.getFloat("price")+"</td>");
out.print("</TR>");
}
out.println("</table>");
con.close();
}
catch(SQLException e){
	out.print(e);	
}
%>
</body>
</html>