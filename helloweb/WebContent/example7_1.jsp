<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%> 
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Insert title here</title>
</head>
<body>
<% Connection con;
Statement sql;
ResultSet rs;
try{ Class.forName("com.mysql.jdbc.Driver");
}
catch(Exception e){
out.println("忘记把MYSQL数据库的JDBC数据库驱动程序复制到JDK的扩展目录中");
}
try{String uri="jdbc:mysql://127.0.0.1/warehouse?useUnicode=true&characterEncoding=gb2312&serverTimezone=GMT";
String user="root";
String password="123456";
con=DriverManager.getConnection(uri,user,password);
sql=con.createStatement();
rs=sql.executeQuery("SELECT * FROM product ");
out.println("<table border=2>");
out.println("<tr>");
out.print("<th width=100>"+"产品号");
out.print("<th width=100>"+"名称");
out.print("<th width=50>"+"生产日期");
out.print("<th width=50>"+"价格");
out.print("</TR>");
while(rs.next()){
out.println("<tr>");
out.print("<td>"+rs.getString(1)+"</td>");
out.print("<td>"+rs.getString(2)+"</td>");
out.print("<td>"+rs.getDate("made Time")+"</td>");
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