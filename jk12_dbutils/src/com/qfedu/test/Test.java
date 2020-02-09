package com.qfedu.test;

import com.qfedu.bean.Student;
import com.qfedu.bean.User;
import com.qfedu.dbutils.DBUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*User user=new DBUtils().getUser("select * from user where username=? and password=?", "zhangsan","123456");
		System.out.println(user);*/
		
		String sql="select * from user where username=? and password=?";
		User user=new DBUtils<User>(User.class).getBean(sql, "zhangsan","123456");
		System.out.println(user);
		
		Student student=new DBUtils<Student>(Student.class).getBean("select * from student where id=?", 1);
		System.out.println(student);
	}

}
