package myservlet.control;
import mybean.data.Bean7_1;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/Shiyan1_Servlet")
public class Shiyan1_Servlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		public void init(ServletConfig config)throws ServletException{
			super.init(config);
			try{ Class.forName("com.mysql.jdbc.Driver");}
			catch(Exception e) {}
			} 
		public void fail(HttpServletRequest request,HttpServletResponse response,String backNews) {
				response.setContentType("text/html);charset=utf-8");
				try {
					PrintWriter out=response.getWriter();
					out.println("<html><body>");
					out.println("<h2>"+backNews+"</h2>");
					out.println("����");
					out.println("<a href=inputDatabase.jsp>������ȷ��Ϣ</a>");
					out.println("</body></html>");

				}
			catch(IOException exp) {}
		}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
ServletException, IOException {
request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String dataBase=request.getParameter("dataBase");
			String tablename=request.getParameter("tablename");
			String user=request.getParameter("user");
			String password=request.getParameter("password");
			boolean boo=(dataBase==null||dataBase.length()==0);
			boo=boo||(tablename==null||tablename.length()==0);
			boo=boo||(user==null||user.length()==0);
			if(boo) {
				fail(request,response,"��ѯʧ��");
			}
			HttpSession session=request.getSession(true);
			Connection con=null;
			Bean7_1 recordBean=null;
			try {
				recordBean=(Bean7_1)session.getAttribute("recordBean");
				if(recordBean==null) {
					recordBean=new Bean7_1();		//����javaBean����
					session.setAttribute("recordBean",recordBean);
				}
			}
			catch(Exception exp) {
				recordBean=new Bean7_1();
				session.setAttribute("recordBean", recordBean);
			}
			String uri="jdbc:mysql://127.0.0.1/"+dataBase;
			try {
				con=DriverManager.getConnection(uri, user, password);
				Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
ResultSet.CONCUR_READ_ONLY);
				ResultSet rs=sql.executeQuery("SELECT * FROM "+tablename);
				ResultSetMetaData metaData=rs.getMetaData();
				int columnCount=metaData.getColumnCount();			//�õ����������
				String[]colname=new String[columnCount];
				for(int i=0;i<colname.length;i++) {
					colname[i]=metaData.getColumnName(i+1);		//�õ�����
				}
				recordBean.setColname(colname);						//����javabean����ģ��
				rs.last();
				int rowNumber=rs.getRow();							//�õ���¼��
				String[][]tableRecord=recordBean.getTablerecord();
				tableRecord=new String[rowNumber][columnCount];
				rs.beforeFirst();
				int i=0;
				while(rs.next()) {
					for(int k=0;k<columnCount;k++)
						tableRecord[i][k]=rs.getString(k+1);
					i++;	
				}
				recordBean.setTablerecord(tableRecord);				//����javabean����ģ��
				con.close();
				response.sendRedirect("inputDatabase.jsp");			//�ض���
				}
			catch(SQLException e) { System.out.println(e);}
			}
}
