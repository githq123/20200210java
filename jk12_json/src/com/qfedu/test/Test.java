package com.qfedu.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user=new User();
		user.setId(1);
		user.setUsername("张三");
		user.setPassword("123456");
		user.setSex("男");
		user.setAge(18);
		System.out.println(user.toString());
		
		User user1=new User();
		user1.setId(2);
		user1.setUsername("李四");
		user1.setPassword("666666");
		user1.setSex("女");
		user1.setAge(19);
		//产生JsonObject
		JSONObject jsonObject=JSONObject.fromObject(user);
		System.out.println(jsonObject);
		
		
		List<User> list=new ArrayList<User>();
		list.add(user);
		list.add(user1);
		//产生JsonArray
		JSONArray jsonArray=JSONArray.fromObject(list);
		System.out.println(jsonArray);
		
		//内部有 引用类型成员的对象
		//产生json的时候，引用类型如果是普通对象型，引用的成员也会变成jsonObject
		//如果引用类型是集合类型，引用的成员将会变为jsonArray
		
		
		UserManager userManager=new UserManager();
		userManager.setId(98);
		userManager.setName("管理员");
		userManager.setUser(user);
		userManager.setUsers(list);
		
		JSONObject managerObject=JSONObject.fromObject(userManager);
		System.out.println(managerObject);
		
	}

}
