package as;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
		
public class StudentDao {
	private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
    private PreparedStatement statement;
    
    public boolean queryUser( String name,String pwd){
    	boolean flag=false;
        String sql="select * from student where sname='"+name+"' and spwd ='"+pwd+"';";
        System.out.println("sql="+sql);
        try {
            conn=DBUtil.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            System.out.println(ps.toString());
       	    while(rs.next()){
       	    	flag=true;
             }
       	    
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return flag;
    }
    
    public List<Student> queryAllUser(){
    	List<Student> list=new ArrayList<Student>();
        String sql="select * from student";
        try {
            conn=DBUtil.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            System.out.println(ps.toString());
       	    while(rs.next()){
       	    	Student p=new Student();
 				//p.setNo(rs.getString("Sno"));
 				p.setName(rs.getString("Sname"));
 				p.setPwd( rs.getString("Spwd"));
 				//p.setAge(rs.getInt("Sage"));
 				//p.setDept(rs.getString("Sdept"));
                list.add(p);
             }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return list;
    }
    public void InsertUser(Student p) {
    	String sql = "Insert into student values('"+p.getName()+"','"+p.getPwd()+"')";
    	
    	try {
			conn = DBUtil.getConnection();
			statement = conn.prepareStatement(sql);
			try {
				statement.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				 JOptionPane.showMessageDialog(null, "name", "pwd", JOptionPane.ERROR_MESSAGE);
			}
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public void DeleteUser(String s) {
    	String sql = "delete from student where Sno="+ s;
    	try {
    		conn = DBUtil.getConnection();
			Statement stat = conn.createStatement();
			if (stat.executeUpdate(sql) == 0) {
				JOptionPane.showMessageDialog(null, "name", "pwd", JOptionPane.ERROR_MESSAGE);
			}
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
