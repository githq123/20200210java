import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    
	 static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    
	    static final String DB_URL = "jdbc:mysql://localhost:3306/university"
	    		+"?useUnicode=true"
	    		+"&characterEncoding=utf8"
	    		+"&serverTimezone=Asia/Shanghai"
	    		+"&useSSL=false";
	 

	    static final String USER = "root";
	    static final String PASS = "123456";
	 
    
    static{
          try {  
              Class.forName(JDBC_DRIVER);         
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
    }
        
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DB_URL,USER,PASS);
    }
    

    public  static void close(ResultSet rs, Statement stat, Connection conn) throws SQLException{
        if(rs!=null){
            rs.close();
        }if(stat!=null){
            stat.close();
        }if(conn!=null){
            conn.close();
        }
    }    
    
}    
